<%-- 
    Document   : modify
    Created on : Nov 11, 2014, 10:57:16 AM
    Author     : July
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update your Profile </title>
    </head>
    <body>
        <h1>Update your profile</h1>
        <form action="Update" method="POST">
            <p>
                <label>First Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="text" name="first_name" /><br/>
            </p>
            <p>
                <label>Last Name:</label>
                <input type="text" name="last_name" /><br/>
            </p>
            <p>
                <label>Major:</label>
                <input type="text" name="major" /><br/>
            </p>
            <p>
                <label>Gender:</label>
                <input type="text" name="gender" /><br/>
            </p>
            <p>
                <label>Ad Year:</label>
                <input type="text" name="ad_year" /><br/>
            </p>
            <input type="hidden" name="action" value="update">
            <input type="submit">
        </form>
    </body>
</html>
