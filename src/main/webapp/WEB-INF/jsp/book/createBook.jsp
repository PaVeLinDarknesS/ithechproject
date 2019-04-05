<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>

<html lang="en">

<head>
    <title>Create</title>

    <script type="text/javascript">
        function confirmCreate() {
            var message = document.getElementsById('title').value;
            if (confirm("Do you want save book '"+message+"'?")) {
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
        <form action="/book/create" method="POST">
        
            <p><b>Title:</b><br>
                <input name="title"/>
            </p>
            
            <p><b>Year:</b><br>
                <input name="year" value="${book.getYear()}"/>
            </p>

            <p><b>Count:</b><br>
                <input name="count" type="number" value="0"/>
            </p>

            <hr />

            <input type="submit" name="button" value="Create" onclick="return confirmCreate()"> 
        
        </form>
        <a href="/book/">All Books</a>
    </div>
</body>
</html>