package com.examole;

import com.examole.model.Book;
import com.examole.model.DbConnection;
import com.examole.model.Gender;
import com.examole.model.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.examole.CreateBookServlet.bookListHash;

@WebServlet(name = "CreateAndUpdateUserServlet", value = "/CreateAndUpdateUserServlet")
public class CreateAndUpdateUserServlet extends HttpServlet {
    final DbConnection dbc = new DbConnection();
     public static Map<Integer, Person> personListHash = new HashMap<>();
    Person person;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbc.connect();
        getQueryUser(dbc);
        System.out.println("tets1");
        int id = Integer.parseInt(request.getParameter("id"));
        if (personListHash.containsKey(id)) {
            person = personListHash.get(id);
            person.setName(request.getParameter("name"));
            person.setAge(Integer.parseInt(request.getParameter("age")));
            String genderStr = request.getParameter("gender");
            person.setGender(Gender.valueOf(genderStr.toUpperCase()));
            updateQueryUser(dbc, person);
            System.out.println(person);
        }
        response.sendRedirect("menu.jsp");
        dbc.closeConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbc.connect();
        getQueryUser(dbc);

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String genderStr = request.getParameter("gender");
        Gender gender = Gender.valueOf(genderStr.toUpperCase());
        person = new Person(name, age, gender);
        insertQueryUser(dbc, person);

        response.sendRedirect("menu.jsp");
        dbc.closeConnection();
    }

     static void getQueryUser(DbConnection dbConnection) {
        String selectQuery = "SELECT * FROM users1";
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                String name = resultSet.getString("full_name");
                String genderStr = resultSet.getString("gender");
                Gender gender = Gender.valueOf(genderStr);
                String[] borrowedBooks1;
                borrowedBooks1 = resultSet.getString("borrowedBook").split(", ");
                Book[] borrowedBooks = getBorrowedBooksDB(borrowedBooks1);
                Person person = new Person(name, age, gender);
                person.setUserId(id);
                person.setBorrowedBooks(borrowedBooks);
                personListHash.put(person.getUserId(), person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Book[] getBorrowedBooksDB(String[] borrowedBooks1) {
        Book[] borrowedBooks = new Book[10];

        for (int i = 0; i < borrowedBooks1.length; i++) {
            String borrowedBookName = borrowedBooks1[i];

            for (Map.Entry<Integer, Book> entry : bookListHash.entrySet()) {
                Book book = entry.getValue();
                if (book != null && borrowedBookName.contains(book.getName())) {
                    borrowedBooks[i] = book;
                    break;
                }
            }
        }

        return borrowedBooks;
    }

    private static void insertQueryUser(DbConnection dbConnection, Person person) {
        String insertQuery = "INSERT INTO users1 (id, age, full_name, gender, borrowedBook) VALUES (id.NEXTVAL, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, person.getAge());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getMGender().toString());
            preparedStatement.setString(4, person.getBorrowedBooksString());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.closeConnection();
    }

     static void updateQueryUser(DbConnection dbConnection, Person person) {
        String updateQuery = "UPDATE users1 SET age = ?, full_name = ?, gender = ?, borrowedbook =? WHERE id = ?";
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, person.getAge());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getMGender().toString());
            preparedStatement.setString(4, person.getBorrowedBooksString());
            preparedStatement.setInt(5, person.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();
            personListHash.put(person.getUserId(), person);
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}