
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>

<html lang="en">
    <head>
        <title>All Books</title>
    </head>
    <body>
        <jsp:include page="../parts/header.jsp" flush="true"/>
        
        <ol>
            <c:forEach items="${errors}" var="error">
                <li> <b>
                    ${error.defaultMessage}
                </b>
                </li>
            </c:forEach>
        </ol>
        
        <form action="/book/user/delete" method="POST">
            <div>
                <c:forEach items="${books}" var="book">
                    <a href="/book/${book.getId()}">
                        ${book.toString()}
                    </a>
                    <input type="checkbox" name="books" value="${book.getId()}"/>        
                    <br />
                </c:forEach>
               <input type="submit" value="Delete yourself">
            </div>
        </form>
        
        <div>
            <b>
                ${message}
            </b>
        </div>
        <jsp:include page="../parts/footer.jsp" flush="true"/>
    </body>
</html>