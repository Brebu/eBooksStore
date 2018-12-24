<%-- 
    Document   : eBooksStoreExit.jsp
    Created on : Dec 22, 2018, 5:03:39 PM
    Author     : Ciprian Alexandru Lucian Brebu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exit</title>
        <link rel="stylesheet" type="text/css" href=".\\css\\ebookstore.css">
        <link rel="stylesheet" type="text/css" href=".\\css\\menu.css"> 
        <link rel="icon" href=".\\img\\favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <%-- test if a valid user is logged in --%>
        <c:choose>
            <c:when test="${validUser == true}"> 
                <c:set var="validUser" value="false" scope="session"></c:set>
                <c:set var="actualUser" value="" scope="session"></c:set>  
                <c:set var="actualUserRole" value=""scope="session" ></c:set>
                <c:redirect url="./Index.jsp"></c:redirect> 
            </c:when>
            <c:otherwise> 
                <c:redirect url="./Index.jsp"></c:redirect>
            </c:otherwise>
        </c:choose>
    </body>
</html>
