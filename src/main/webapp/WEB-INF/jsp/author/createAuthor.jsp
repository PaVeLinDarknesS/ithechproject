<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>

<html lang="en">

<head>
    <title>Create Author</title>

    <script type="text/javascript">
        function confirmCreate() {
            return confirm("Do you want save new author ?");
        }
    </script>
</head>

<body>
    <div>
        <ol>
            <c:forEach items="${errors}" var="error">
                <li> <b>
                    ${error.defaultMessage}
                </b>
                </li>
            </c:forEach>
        </ol>
    </div>
    <div>
        <form action="/author/create" method="POST">
        
            <p><b>First name:</b><br>
                <input name="firstName" value="${author.getFirstName()}"/>
            </p>
            
            <p><b>Last name:</b><br>
                <input name="lastName" value="${author.getLastName()}"/>
            </p>

            <hr />

            <input type="submit" name="button" value="Create" onclick="return confirmCreate()"> 
        
        </form>
        <a href="/author/">All Authors</a>
    </div>
</body>
</html>