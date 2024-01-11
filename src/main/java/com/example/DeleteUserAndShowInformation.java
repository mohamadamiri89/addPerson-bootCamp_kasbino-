package com.examole;

import com.examole.model.DbConnection;
import com.examole.model.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.examole.CreateAndUpdateUserServlet.getQueryUser;
import static com.examole.CreateAndUpdateUserServlet.personListHash;

@WebServlet(name = "DeleteUserAndShowInformation", value = "/DeleteUserAndShowInformation")
public class DeleteUserAndShowInformation extends HttpServlet {
    final DbConnection dbc = new DbConnection();
    Person person;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbc.connect();
        getQueryUser(dbc);
        int id = Integer.parseInt(request.getParameter("id"));
        person = personListHash.get(id);
        DeleteQueryUser(dbc, person);
        personListHash.remove(id);
        dbc.closeConnection();
        response.sendRedirect("menu.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbc.connect();
        getQueryUser(dbc);
        int id = Integer.parseInt(request.getParameter("id"));
        person = personListHash.get(id);
        request.setAttribute("person", person);
        RequestDispatcher rd = request.getRequestDispatcher("ShowUserInformation.jsp");
        rd.forward(request, response);
        response.sendRedirect("ShowUserInformation.jsp\n");
        dbc.closeConnection();
    }

    private static void DeleteQueryUser(DbConnection dbConnection, Person person) {
        String deleteQuery = "DELETE FROM users1 WHERE id = ?";
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, person.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}