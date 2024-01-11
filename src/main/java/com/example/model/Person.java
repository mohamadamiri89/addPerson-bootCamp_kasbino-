package com.examole.model;

public class Person {

    String mName;
    int mAge;
    Gender mGender;

    private int mUserId;

    private Book[] borrowedBooks = new Book[10];

    public void setBorrowedBooks(Book[] borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public Person(String mName, int mAge, Gender mGender) {
        this.mName = mName;
        this.mAge = mAge;
        this.mGender = mGender;
    }
    /**
     * It's a method to get array of borrowed books for every person(object)
     */


    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getBorrowedBooksString() {
        StringBuilder sb = new StringBuilder();
        if (borrowedBooks != null) {
            sb.append("'(");
            boolean first = true;
            for (Book book : borrowedBooks) {
                if (book != null) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(book.getName());
                    first = false;
                }
            }
            sb.append(")'");
        }else sb.append("()");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User { ");
        sb.append("Name=").append(mName);
        sb.append(", Age=").append(mAge);
        sb.append(", Gender=").append(mGender);
        sb.append(", UserId=").append(mUserId);

        if (borrowedBooks != null) {
            sb.append(", borrowedBooks=[");

            boolean first = true;
            for (Book book : borrowedBooks) {
                if (book != null) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(book.getName());
                    first = false;
                }
            }

            sb.append("]");
        }

        sb.append(" }");
        return sb.toString();
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int mAge) {
        this.mAge = mAge;
    }

    public Gender getMGender() {
        return mGender;
    }

    public void setGender(Gender mGender) {
        this.mGender = mGender;
    }

}