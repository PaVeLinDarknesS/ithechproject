<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header style="position: relative; top: 0; width: 100%; border: 1px double black;">
    <a href="/book/">All Books</a>
    <a href="/author/">All Authors</a>

    <sec:authorize access="isAuthenticated()">
        User - '<sec:authentication property="principal.username" />'
        <a href="/book/user/">My books</a>
        <a href="/logout">Logout</a>
    </sec:authorize>
    
    <sec:authorize access="isAnonymous()">
        <a href="/login">Login</a>
    </sec:authorize>
</header>
<hr />