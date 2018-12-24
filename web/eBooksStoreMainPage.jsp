<%-- 
    Document   : eBooksStoreMainPage.jsp
    Created on : Dec 22, 2018, 5:03:39 PM
    Author     : Ciprian Alexandru Lucian Brebu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>You are in main page of BREBooksStore</title>
        <link rel="stylesheet" type="text/css" href=".\\css\\ebookstore.css">
        <link rel="stylesheet" type="text/css" href=".\\css\\menu.css">   
        <link rel="icon" href=".\\img\\favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <h1>You are in main page of <span class="logoname"><span class="bre">BRE</span>BooksStore</span></h1>
        <c:choose>
            <c:when test="${validUser == true}">
                <%@ include file="./utils/eBooksStoreMenu.jsp" %>
            </c:when>
            <c:otherwise>
                <c:redirect url="./index.jsp"></c:redirect>
            </c:otherwise>
        </c:choose>
    </body> 
</html>
