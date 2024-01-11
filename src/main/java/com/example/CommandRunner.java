package com.example;

import com.example.model.DbConnection;
import com.example.model.Gender;
import com.example.model.Person;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CommandRunner {
    private final DbConnection dbConnection;

    public CommandRunner(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void insertPerson(Person person) {
        String insertQuery = "INSERT INTO person (age, full_name, gender) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = dbConnection.getConnection()
                .prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, person.getAge());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getMGender().toString());

            int rowsAffected = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    person.setUserId(generatedId);
                }
            }

            System.out.println(rowsAffected + " row(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(Person person) {
        String updateQuery = "UPDATE person SET age = ?, full_name = ?, gender = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, person.getAge());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getMGender().toString());
            preparedStatement.setInt(4, person.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Person> getAllPersons() {
        Map<Integer, Person> personListHash = new HashMap<>();
        String selectQuery = "SELECT * FROM person";
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                String name = resultSet.getString("full_name");
                String genderStr = resultSet.getString("gender");
                Gender gender = Gender.valueOf(genderStr);
                Person person = new Person(name, age, gender);
                person.setUserId(id);
                personListHash.put(person.getUserId(), person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personListHash;
    }
    public static void runCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", command);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println("Command executed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
