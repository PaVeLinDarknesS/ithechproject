
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>

<html lang="en">
    <head>
        <title>All Books</title>
    </head>
    <body>
            <a href="/book/create">Add Book</a>
        <div>
            <ol>
                <c:forEach items="${books}" var="item">
                    <li> 
                        <a href="/book/${item.getId()}">
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