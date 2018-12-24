<%-- 
    Document   : eBooksStoreAdminEBooks
    Created on : Dec 22, 2018, 5:03:39 PM
    Author     : Ciprian Alexandru Lucian Brebu
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="WEB-INF/tlds/astiro.tld" prefix="astiro" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage the ebooks from BREBooksStore Management </title>
        <link rel="stylesheet" type="text/css" href=".\\css\\ebookstore.css">
        <link rel="stylesheet" type="text/css" href=".\\css\\menu.css"> 
        <link rel="icon" href=".\\img\\favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <h1>Manage the ebooks from <span class="logoname"><span class="bre">BRE</span>BooksStore</span> Management </h1>
        <%-- test if actual user is authenticated and authorized --%>
        <c:choose>
            <c:when test="${validUser == true}">   
                <!-- include menu -->
                <%@ include file="./utils/eBooksStoreMenu.jsp" %>
                <%-- Master view --%>
                <form action="${pageContext.request.contextPath}/eBooksStoreAdminEBooks" method="POST">  
                    <%-- usage of JSTL tag setDataSource for DB connection 
                <sql:setDataSource 
                var="snapshot" 
                driver="org.apache.derby.jdbc.ClientDriver40"
                url="jdbc:derby://localhost:1527/ebooksstore;create=true;"
                user="test"  
                password="test"/>
                    --%>
                    <%-- usage of user defined tag to make connection to DB --%>
                    <astiro:databseconnection
                        conexiune="snapshot" 
                        driver="org.apache.derby.jdbc.ClientDriver40"
                        url="jdbc:derby://localhost:1527/ebooksstore;create=true;"
                        username="test"  
                        password="test"/>
                    <sql:query dataSource="${snapshot}" var="result">
                        SELECT ISBN, TITLE, TYPE, QUALITY, PAGES, GENRE, PRICE FROM EBOOKS.EBOOKS 
                        JOIN EBOOKS.BOOK_TYPES ON ID_TYPE=EBOOKS.BOOK_TYPES.ID
                        JOIN EBOOKS.BOOK_GENRES ON ID_GENRE=EBOOKS.BOOK_GENRES.ID
                        JOIN EBOOKS.BOOK_PAPER_QUALITIES ON ID_QUALITY=EBOOKS.BOOK_PAPER_QUALITIES.ID
                    </sql:query>
                        <table border="1" width="100%">
                            <tr>
                                <td width="4%" class="thc"> select </td>   
                                <td width="12%" class="thc">ISBN</td>  
                                <td width="12%" class="thc">TITLE</td>
                                <td width="12%" class="thc">TYPE</td>
                                <td width="12%" class="thc">QUALITY</td>
                                <td width="12%" class="thc">PAGES</td>
                                <td width="12%" class="thc">GENRE</td>
                                <td width="12%" class="thc">PRICE</td>
                            </tr>
                            <c:forEach var="row" varStatus="loop" items="${result.rows}">
                                <tr>
                                    <td width="4%" class="tdc"><input type="checkbox" name="admin_ebooks_checkbox" value="${row.isbn}"></td>
                                    <td width="12%" class="tdc"><c:out value="${row.isbn}"/></td>
                                    <td width="12%" class="tdc"><c:out value="${row.title}"/></td>
                                    <td width="12%" class="tdc"><c:out value="${row.type}"/></td>
                                    <td width="12%" class="tdc"><c:out value="${row.quality}"/></td>
                                    <td width="12%" class="tdc"><c:out value="${row.pages}"/></td>
                                    <td width="12%" class="tdc"><c:out value="${row.genre}"/></td>
                                    <td width="12%" class="tdc"><c:out value="${row.price}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <%-- Details --%>
                        <sql:setDataSource 
                            var="snapshotgenres" 
                            driver="org.apache.derby.jdbc.ClientDriver40"
                            url="jdbc:derby://localhost:1527/ebooksstore;create=true;"
                            user="test"  
                            password="test"/>
                        <sql:query dataSource="${snapshotgenres}" var="resultgenres">
                            SELECT ID, GENRE FROM EBOOKS.BOOK_GENRES 
                        </sql:query>
                        <sql:setDataSource 
                            var="snapshotpaperqualities" 
                            driver="org.apache.derby.jdbc.ClientDriver40"
                            url="jdbc:derby://localhost:1527/ebooksstore;create=true;"
                            user="test"  
                            password="test"/>
                        <sql:query dataSource="${snapshotpaperqualities}" var="resultpaperqualities">
                            SELECT ID, QUALITY FROM EBOOKS.BOOK_PAPER_QUALITIES 
                        </sql:query>    
                        <sql:setDataSource 
                            var="snapshottypes" 
                            driver="org.apache.derby.jdbc.ClientDriver40"
                            url="jdbc:derby://localhost:1527/ebooksstore;create=true;"
                            user="test"  
                            password="test"/>
                        <sql:query dataSource="${snapshottypes}" var="resulttypes">
                            SELECT ID, TYPE FROM EBOOKS.BOOK_TYPES 
                        </sql:query>    
                        <table class="tablecenterdwithborder">
                            <tr>
                                <td>    
                                    <table>
                                        <tr>
                                            <td> ISBN: </td>
                                            <td> <input type="text" name="admin_ebooks_isbn"></input></td>
                                        </tr>                                        
                                        <tr>
                                            <td> TITLE: </td>
                                            <td> <input type="text" name="admin_ebooks_title"></input></td>
                                        </tr>
                                        <tr>
                                            <td> PAGES NO: </td>
                                            <td> <input type="number" name="admin_ebooks_pages"></input></td>
                                        </tr>
                                        <tr>
                                            <td> PRICE: </td>
                                            <td> <input type="number" step="0.01" name="admin_ebooks_price"></input></td>
                                        </tr>
                                        <tr>
                                            <td> TYPE: </td>
                                            <td>
                                                <select name="admin_ebooks_id_type" required="true">
                                                    <c:forEach var="rowtype" items="${resulttypes.rows}">    
                                                        <option name="admin_ebooks_types" value="${rowtype.ID}">${rowtype.ID}.${rowtype.TYPE}</option>
                                                    </c:forEach>
                                                    <option selected="selected" name="admin_ebooks_types" value=""></option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> PAPER QUALITY: </td>
                                            <td>
                                                <select name="admin_ebooks_id_paper_qualities" required="true">
                                                    <c:forEach var="rowquality" items="${resultpaperqualities.rows}">    
                                                        <option name="admin_ebooks_paper_qualities" value="${rowquality.ID}">${rowquality.ID}.${rowquality.quality}</option>
                                                    </c:forEach>
                                                    <option selected="selected" name="admin_ebooks_paper_qualities" value=""></option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> GENRE: </td>
                                            <td>
                                                <select name="admin_ebooks_id_genres" required="true">
                                                    <c:forEach var="rowgenre" items="${resultgenres.rows}">    
                                                        <option name="admin_ebooks_genres" value="${rowgenre.ID}">${rowgenre.ID}.${rowgenre.genre}</option>
                                                    </c:forEach>
                                                    <option selected="selected" name="admin_ebooks_genres" value=""></option>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <%-- buttons --%>
                                    <table>
                                        <tr><td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_ebooks_insert" value="Insert"></td> 
                                            <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_ebooks_update" value="Update"></td>
                                            <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_ebooks_delete" value="Delete"></td> 
                                            <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_ebooks_cancel" value="Cancel"></td>
                                        </tr>  
                                    </table>
                                </td>
                            </tr>
                        </table>    
                    </c:when>
                    <c:otherwise>
                        <c:redirect url="./Index.jsp"></c:redirect>
                    </c:otherwise>
                </c:choose>
        </form>
    </body>
</html>

