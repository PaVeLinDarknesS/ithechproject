<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
    <title>Login</title>
</head>

<body>
    <div>
        <h2>Welcome to Online Library</h2>
        <h3>${sessionScope.userKey}</h3>
        <br />
        
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
        
        <form action="/login" method="POST">
            <div>
                <input type="text" name="login" placeholder="Enter login" />
                <br />
                <input type="password" name="password" placeholder="Enter password" />
            </div>

            <input type="submit" value="Enter" />
        </form>

        <div>
            <b>
                ${message}
            </b>
        </div>

    </div>
</body>

</html>