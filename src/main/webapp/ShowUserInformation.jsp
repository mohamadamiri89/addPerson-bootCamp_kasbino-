<%@ page import="com.examole.model.Person" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            background-color: #000;
            color: #fff;
            font-family: 'Helvetica Neue', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        h1 {
            font-size: 30px;
            color: #BF2E97;
            text-align: center;
        }

        table {
            width: 70%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #333;
        }

        th {
            background-color: #333;
            color: #fff;
        }

        tbody tr:nth-child(even) {
            background-color: #444;
        }

        tbody tr:nth-child(odd) {
            background-color: #555;
        }

        ul {
            list-style-type: none;
        }

        li {
            padding: 10px;
            text-transform: uppercase;
        }

        a {
            text-decoration: none;
            color: #fff;
        }
    </style>
    <title>User Information</title>
</head>
<body>
<h1>User Information</h1>

<%
    Person person = (Person) request.getAttribute("person");
    if (person != null) {
%>
<div class="add-card">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Borrowed Books</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%= person.getUserId()  %></td>
            <td><%= person.getName() %></td>
            <td><%= person.getAge() %></td>
            <td><%= person.getMGender() %></td>
            <td><%= person.getBorrowedBooksString() %></td>
        </tr>
        </tbody>
    </table>
</div>
<%
} else {
%>
<p>User not found.</p>
<%
    }
%>
<ul>
    <li><a href="menu.jsp">back to menu</a></li>
</ul>
</body>
</html>
