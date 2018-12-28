<%-- 
    Document   : index
    Created on : Dec 22, 2018, 5:02:39 PM
    Author     : Ciprian Alexandru Lucian Brebu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to BREBooksStore</title>
        <link rel="stylesheet" type="text/css" href=".\\css\\ebookstore.css">
        <link rel="stylesheet" type="text/css" href=".\\css\\menu.css"> 
        <link rel="icon" href=".\\img\\favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <h1>Welcome to <span class="logoname"><span class="bre">BRE</span>BooksStore</span></h1>
        <c:set var="activePage" value="index" scope="session"></c:set>
        <%-- <hr> --%>
        <!-- delegation of authentication and authorization is done to associated Servlet -->
            <form action="${pageContext.request.contextPath}/Index" method="POST">
                <table class="tablecenterdwithborder">
                <tr><td>Username: </td><td><input class = "inputlarge" type="text" name="authenticationpage_username"></input></td></tr> 
                <tr><td>Password: </td><td><input class = "inputlarge" type="password" name="authenticationpage_password"></input></td></tr> 
                <tr><td></td><td><input type="submit" name="authenticationpage_authenticate" value="Login"></input></td></tr>
                </table>
            </form>
    </body>
</html>
