<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>

<html lang="en">

<head>
    <title>${book.getTitle()}</title>

    <script type="text/javascript">
        function confirmUpdate() {
            if (confirm("Do you want update book '${book.getTitle()}'?")) {
                return true;
            } else {
                return false;
            }
        }
    </script>
</head>

<body>
    <div>
        <ol>
            <c:forEach items="${errors}" var="bug">
                <li> <b>
                    ${bug.defaultMessage}
                </b>
                </li>
            </c:forEach>
        </ol>
    </div>
    <div>
        <form action="/book/${book.getId()}/update" method="POST">
            <input id="id" type="hidden" value="${book.getId()}">
        
            <p><b>Title:</b><br>
                <input name="title" value="${book.getTitle()}"/>
            </p>
            
            <p><b>Year:</b><br>
                <input name="year" value="${book.getYear()}"/>
            </p>
            
            <p><b>Count:</b><br>
                <input name="count" value="${book.getCount()}"/>
            </p>

            <hr />
            <input type="submit" name="button" value="Save" onclick="return confirmUpdate()">
        </form>
        <a href="/book/">All Books</a>
    </div>
</body>
</html>