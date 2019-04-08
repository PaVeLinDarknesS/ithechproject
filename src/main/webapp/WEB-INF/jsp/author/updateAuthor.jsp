<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>

<html lang="en">

<head>
    <title>${author.toString()}</title>

    <script type="text/javascript">
        function confirmUpdate() {
            return confirm("Do you want update author '${author.toString()}'?");
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
        <form action="/author/${author.getId()}/update" method="POST">
            <input id="id" type="hidden" value="${author.getId()}">
        
            <p><b>First name:</b><br>
                <input name="firstName" value="${author.getFirstName()}"/>
            </p>
            
            <p><b>Last name:</b><br>
                <input name="lastName" value="${author.getLastName()}"/>
            </p>

            <hr />
            <input type="submit" name="button" value="Save" onclick="return confirmUpdate()">
        </form>
        <a href="/author/">All Authors</a>
    </div>
</body>
</html>