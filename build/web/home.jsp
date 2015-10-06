<%-- 
    Document   : home
    Created on : Nov 15, 2014, 4:39:01 PM
    Author     : July
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        <h2>${user.getFirstName().toString()}  ${user.getLastName().toString()}</h2>
        
        <p>
            Your current balance : ${user.balance}
        </p>
        
        <form action="Home">
            <input type="hidden" name="action" value="logout"/>
            <input type ="submit" value="logout"/>
        </form>
        <form action="Transfer">
            <input type="hidden" name="action" value="transfer"/>
            <input type ="submit" value="transfer"/>
        </form>
        
    </body>
</html>
