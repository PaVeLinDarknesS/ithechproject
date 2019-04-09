<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.itech.library.entity.*" %>

<html lang="en">

<head>
    <title>${author.toString()}</title>
    <script type="text/javascript">
        function confirmDelete() {
            return confirm("Do you want delete author '${author.toString()}'");
        }
    </script>
</head>

<body>
    <div>
        <b>First name</b> - ${author.getFirstName()}, <br />
        <b>Last name</b> - ${author.getLastName()}, <br />
        <hr />
        <b>Books</b> -
        <c:choose>
            <c:when test="${!books.isEmpty()}">
                <c:forEach items="${books}" var="book">
                    <li> 
                        <a href="/book/${book.getId()}">
                            ${book.toString()}
                        </a>
                    </li>
                </c:forEach>
            </c:when>    
            <c:otherwise>
                <strike> not </strike>
            </c:otherwise>
    </c:choose>
        <hr />
        <hr />

        <a href="/author/${author.getId()}/update">Update</a>

        <form action="/author/${author.getId()}/delete" method="POST">
            <input type="submit" name="button" value="Delete" onclick="return confirmDelete()">
        </form>
    </div>
</body>
</html>