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

        form {
            text-align: center;
        }

        label {
            display: block;
            margin: 10px 0;
        }

        input[type="number"] {
            width: 100%;
            padding: 10px;
        }

        input[type="submit"] {
            background-color: #BF2E97;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
    </style>
    <title>User Information</title>
</head>
<body>
<h1>Show Information of User</h1>
<form action="DeleteUserAndShowInformation" method="post">
    <label for="id">ID:</label>
    <input type="number" id="id" name="id" required>
    <br><br>
    <input type="submit" value="Show User Information">
</form>
</body>
</html>
