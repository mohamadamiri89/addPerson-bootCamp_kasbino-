package com.example;

import com.example.model.DbConnection;
import com.example.model.Gender;
import com.example.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateAndUpdateUserServlet", value = "/CreateAndUpdateUserServlet")
public class CreateAndUpdateUserServlet extends HttpServlet {
    private final DbConnection dbc = new DbConnection();
    private CommandRunner commandRunner;

    @Override
    public void init() throws ServletException {
        super.init();
        dbc.connect();
        commandRunner = new CommandRunner(dbc);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Person person = commandRunner.getAllPersons().get(id);
        if (person != null) {
            person.setName(request.getParameter("name"));
            person.setAge(Integer.parseInt(request.getParameter("age")));
            String genderStr = request.getParameter("gender");
            person.setGender(Gender.valueOf(genderStr.toUpperCase()));
            commandRunner.updatePerson(person);
            System.out.println(person);
        }
        response.sendRedirect("menu.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String genderStr = request.getParameter("gender");
        Gender gender = Gender.valueOf(genderStr.toUpperCase());
        Person person = new Person(name, age, gender);
        commandRunner.insertPerson(person);

        response.sendRedirect("menu.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
        dbc.closeConnection();
    }
}
