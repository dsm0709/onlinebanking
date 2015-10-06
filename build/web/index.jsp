<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Log in</h1>
        <form action="Login" method="POST">
            <p>
                <label>email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="text" name="email" /><br/>
            </p>
            <p>
                <label>password:</label>
                <input type="password" name="password" /><br/>
            </p>
            <input type="hidden" name="action" value="login">
            <input type="submit">
        </form>
        <p>
            <a href="signup.jsp">Sign Up</a>
        </p>
    </body>
</html>
