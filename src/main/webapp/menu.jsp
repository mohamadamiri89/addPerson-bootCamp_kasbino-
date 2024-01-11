<!DOCTYPE html>
<html>
<head>
    <title>User_App</title>
    <link rel="stylesheet" href="menuStyle.css" />
</head>

<body>
<div class="container">
    <h1><a href="#menu">WELCOME  <%= session.getAttribute("username") %></a></h1>
    <br>
    </br>
    <h3><a href="#menu">Click for main menu</a></h3>
</div>

<div class="popover" id="menu">
    <div class="content">
        <a href="#" class="close"></a>
        <ul class="nav_list">
            <li class="nav_list_item"><a href="createUserForm.jsp">Create a new User</a></li>
            <li class="nav_list_item"><a href="EditUser.jsp">Edit User</a></li>
        </ul>
    </div>
</div>
</body>

<footer>
    <h4>MADE BY MOHAMAD AMIRI</h4>
</footer>

</html>
