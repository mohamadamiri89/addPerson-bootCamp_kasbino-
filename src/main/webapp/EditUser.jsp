<!DOCTYPE html>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h1>Update a User</h1>
<form action="CreateAndUpdateUserServlet" method="get">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required><br><br>
    <label for="name">New Name:</label>
    <input type="text" id="name" name="name" required><br><br>
    <label for="age">New Age:</label>
    <input type="number" id="age" name="age" required><br><br>

    <label for="gender">New Gender:</label>
    <select id="gender" name="gender">
        <option value="male">Male</option>
        <option value="female">Female</option>
    </select><br><br>

    <input type="submit" value="Update User">
</form>
</body>
</html>
