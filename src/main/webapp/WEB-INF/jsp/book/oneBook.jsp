<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>
<c:set var="author" value="${book.getAuthor()}" />

<html lang="en">

<head>
    <title>${book.getTitle()}</title>
    <script type="text/javascript">
        function confirmDelete() {
            if (confirm("Do you want delete book '${book.getTitle()}'")) {
                return true;
            } else {
                return false;
            }
        }
    </script>
</head>

<body>
    <div>
        <b>Title</b> - ${book.getTitle()}, <br />
        <b>Year</b> - ${book.getYear()}, <br />
        <b>Count</b> - ${book.getCount()} <br />
        <hr />
        <b>Author</b> -
        <c:choose>
            <c:when test="${author!=null}">
                    ${author.getFirstName()}
                    ${author.getLastName()} 
            </c:when>    
            <c:otherwise>
                <strike> not </strike>
            </c:otherwise>
    </c:choose>
        <hr />
        <hr />

        <a href="/book/${book.getId()}/update">Update</a>

        <form action="/book/${book.getId()}/delete" method="POST">
            <input type="submit" name="button" value="Delete" onclick="return confirmDelete()">
        </form>
    </div>
    <div id="welcome">
    </div>

</body>

</html>