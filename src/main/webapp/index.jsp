<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Email List</title>

```
<style>
    body {
        font-family: Arial, sans-serif;
        background: #f5f6fa;
        margin: 0;
        padding: 40px;
    }

    .container {
        width: 450px;
        margin: 60px auto;
        background: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.1);
    }

    h1 {
        text-align: center;
        margin-bottom: 10px;
    }

    .description {
        text-align: center;
        color: #666;
        margin-bottom: 25px;
    }

    .form-group {
        margin-bottom: 15px;
    }

    label {
        display: block;
        margin-bottom: 6px;
        font-weight: bold;
    }

    input {
        width: 100%;
        padding: 10px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
    }

    input:focus {
        outline: none;
        border-color: #333;
    }

    button {
        width: 100%;
        padding: 11px;
        border: none;
        border-radius: 5px;
        background: #333;
        color: white;
        font-size: 15px;
        cursor: pointer;
        margin-top: 10px;
    }

    button:hover {
        opacity: 0.85;
    }

    .message {
        margin-top: 20px;
        padding: 12px;
        background: #e8f5e9;
        color: #2e7d32;
        border-radius: 5px;
    }

    .error {
        margin-top: 20px;
        padding: 12px;
        background: #ffebee;
        color: #c62828;
        border-radius: 5px;
    }
</style>
```

</head>

<body>

<div class="container">

```
<h1>Join Our Email List</h1>

<p class="description">
    Enter your information below to join our email list.
</p>

<form action="EmailListServlet" method="post">

    <input type="hidden"
           name="action"
           value="add">

    <div class="form-group">

        <label for="firstName">
            First Name
        </label>

        <input type="text"
               id="firstName"
               name="firstName"
               placeholder="Enter your first name"
               required>

    </div>

    <div class="form-group">

        <label for="lastName">
            Last Name
        </label>

        <input type="text"
               id="lastName"
               name="lastName"
               placeholder="Enter your last name"
               required>

    </div>

    <div class="form-group">

        <label for="email">
            Email
        </label>

        <input type="email"
               id="email"
               name="email"
               placeholder="Enter your email"
               required>

    </div>

    <button type="submit">
        Submit
    </button>

</form>

<% if (request.getAttribute("errorMessage") != null) { %>

    <div class="error">
        <%= request.getAttribute("errorMessage") %>
    </div>

<% } %>
```

</div>

</body>
</html>
