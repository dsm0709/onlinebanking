<%-- 
    Document   : success
    Created on : Nov 16, 2014, 3:33:21 PM
    Author     : July
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer Successful</title>
    </head>
    <body>
        <h1>Transfer Successful!</h1>
        <p>
            Transfer to ${recipient_first_name} ${recipient_last_name} :$${amount}
        </p>
        <p>
            <a href="Home">Back to Home</a>
        </p>
    </body>
</html>
