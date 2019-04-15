
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
    <head>
        <title>All Books</title>
    </head>
    <body>
        <jsp:include page="../parts/header.jsp" flush="true"/>

        <sec:authorize access="hasRole('ADMIN')">
            <a href="/book/create">Add Book</a>
            <hr />
        </sec:authorize>
        
        <form action="/book/user/add" method="POST">
            <div>
                <c:forEach items="${books}" var="book">
                    <a href="/book/${book.getId()}">
                        ${book.toString()}
                    </a>
                    <sec:authorize access="isAuthenticated()">
                        <input type="checkbox" name="books" value="${book.getId()}"/>        
                    </sec:authorize>
                    <br />
                </c:forEach>
                <sec:authorize access="isAuthenticated()">
                        <input type="submit" value="Add yourself">
                </sec:authorize>
            </div>
        </form>
        
        <ol>
            <c:forEach items="${errors}" var="error">
                <li> <b>
                    ${error}
                </b>
                </li>
            </c:forEach>
        </ol>

        <div>
            <b>
                ${message}
            </b>
        </div>
        <jsp:include page="../parts/footer.jsp" flush="true"/>
    </body>
</html>