<%-- 
    Document   : transfer
    Created on : Nov 15, 2014, 6:31:51 PM
    Author     : July
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer Page</title>
    </head>
    <body>
        <h1>Transfer Money To Someone</h1>
        <form action="Transfer" >
            <label>The recipient's email Address:</label><br/>
            <input type="text" name="email" /><br/>
            <label>The recipient's first name:</label><br/>
            <input type="text" name="first_name" /><br/>
            <label>The recipient's last name:</label><br/>
            <input type="text" name="last_name" /><br/>
            <label>Amount:</label><br/>
            <input type="text" name="amount" /><br/>
            <input type="hidden" name="code" value="${token_hash}" />
            <input type ="hidden" name="action" value="confirm"/>
            <input type="submit" value="confirm"/>
        </form>
        <p>
            <a href="Home" >Back To Home</a>
        </p>
    </body>
</html>
