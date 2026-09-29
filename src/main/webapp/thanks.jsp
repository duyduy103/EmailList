<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Thank You</title>

```
<style>
    body {
        font-family: Arial, sans-serif;
        background: #f5f6fa;
        margin: 0;
        padding: 40px;
    }

    .container {
        width: 500px;
        margin: 80px auto;
        background: white;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        text-align: center;
    }

    h1 {
        color: #2e7d32;
        margin-bottom: 20px;
    }

    .message {
        font-size: 16px;
        line-height: 1.6;
        color: #444;
    }

    .email {
        font-weight: bold;
        color: #333;
    }

    .button {
        display: inline-block;
        margin-top: 25px;
        padding: 10px 20px;
        background: #333;
        color: white;
        text-decoration: none;
        border-radius: 5px;
    }

    .button:hover {
        opacity: 0.85;
    }
</style>
```

</head>

<body>

<div class="container">

```
<h1>Thank You!</h1>

<div class="message">

    <% if (request.getAttribute("user") != null) { %>

        <p>
            Dear
            <strong>
                ${user.firstName} ${user.lastName}
            </strong>,
        </p>

        <p>
            You have successfully joined our email list.
        </p>

        <p>
            A confirmation email has been sent to:
        </p>

        <p class="email">
            ${user.email}
        </p>

    <% } else { %>

        <p>
            You have successfully joined our email list.
        </p>

    <% } %>

</div>

<a href="index.jsp" class="button">
    Back to Home
</a>
```

</div>

</body>
</html>

    </body>
</html>
