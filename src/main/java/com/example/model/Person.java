package com.example.model;

public class Person {

    String mName;
    int mAge;
    Gender mGender;

    private int mUserId;

    public Person(String mName, int mAge, Gender mGender) {
        this.mName = mName;
        this.mAge = mAge;
        this.mGender = mGender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User { ");
        sb.append("Name=").append(mName);
        sb.append(", Age=").append(mAge);
        sb.append(", Gender=").append(mGender);
        sb.append(", UserId=").append(mUserId);
        sb.append(" } ");

        return  sb.toString();
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