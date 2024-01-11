<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="menuStyle.css">
    <title>Create User </title>
</head>
<body>
<h1 class="form-button">Create a New User </h1>
<form action="CreateAndUpdateUserServlet" method="post">
    <label for="name" class="form-label">Name:</label>
    <input type="text" id="name" name="name" required class="form-input">

    <label for="age" class="form-label">Age:</label>
    <input type="number" id="age" name="age" required class="form-input">

    <label for="gender" class="form-label">Gender:</label>
    <select id="gender" name="gender" class="form-select">
        <option value="male">Male</option>
        <option value="female">Female</option>
    </select>

    <input type="submit" value="Create User" class="form-button">
</form>
</body>
</html>
