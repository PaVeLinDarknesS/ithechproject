<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="author" value="${book.getAuthor()}" />

<html lang="en">

<head>
    <title>${book.getTitle()}</title>
    <script type="text/javascript">
        function confirmDelete() {
            return confirm("Do you want delete book '${book.getTitle()}'");
        }
    </script>
</head>

<body>
        <jsp:include page="../parts/header.jsp" flush="true"/>
    <div>
        <b>Title</b> - ${book.getTitle()}, <br />
        <b>Year</b> - ${book.getYear()}, <br />
        <b>Count</b> - ${book.getCount()} <br />
        <hr />
        <b>Author</b> -
        <c:choose>
            <c:when test="${author!=null}">
                <a href="/author/${author.getId()}">${author.toString()}</a>
            </c:when>    
            <c:otherwise>
                <strike> not </strike>
            </c:otherwise>
        </c:choose>
        <hr />

        <sec:authorize access="hasRole('ADMIN')">
            <a href="/book/${book.getId()}/update">Update</a>
            
            <form action="/book/${book.getId()}/delete" method="POST">
                <input type="submit" name="button" value="Delete" onclick="return confirmDelete()">
            </form>
        </sec:authorize>
    </div>
    <div id="welcome">
    </div>
    <jsp:include page="../parts/footer.jsp" flush="true"/>
</body>
</html>