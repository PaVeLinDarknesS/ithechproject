
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>

<html lang="en">
    <head>
        <title>All Authors</title>
    </head>
    <body>
            <a href="/author/create">Add Author</a>
        <div>
            <ol>
                <c:forEach items="${authors}" var="item">
                    <li> 
                        <a href="/author/${item.getId()}">
                            ${item.toString()}
                        </a>
                    </li>
                </c:forEach>
            </ol>
        </div>
        
        <div>
            <b>
                ${message}
            </b>
        </div>
    </body>

</html>