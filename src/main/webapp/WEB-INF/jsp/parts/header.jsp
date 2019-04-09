<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header style="position: relative; top: 0; width: 100%; border: 1px double black;">
    <a href="/book/">All Books</a>
    <a href="/author/">All Authors</a>

    <c:choose>
        <c:when test="${sessionScope.userKey == null}">
                <a href="/login">Login</a>
        </c:when>    
        <c:otherwise>
            User - '${sessionScope.userKey}'
            <a href="/getBooks/">My books</a>
        </c:otherwise>
    </c:choose>

</header>