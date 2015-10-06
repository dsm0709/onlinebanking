<%-- 
    Document   : signup
    Created on : Nov 11, 2014, 10:09:49 AM
    Author     : July
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
       <h1>Sign Up</h1>
        <form action="SignUp" method="POST">
            <p>
                <label>First Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="text" name="first_name" /><br/>
            </p>
            <p>
                <label>Last Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="text" name="last_name" /><br/>
            </p>
            <p>
                <label>Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="text" name="email" /><br/>
            </p>
            <p>
                <label>Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="password" name="password" /><br/>
            </p>
            <p>
                <label>Confirm:&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="password" name="confirm" /><br/>
            </p>
            <input type="hidden" name="action" value="signup">
            <input type="submit">
        </form>
    </body>
</html>
