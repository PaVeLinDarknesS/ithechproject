
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
        <form action="/user/book/add" method="POST">
            <div>
                <c:forEach items="${books}" var="book">
                    <a href="/book/${book.getId()}">
                        ${book.toString()}
                    </a>
                    <input type="checkbox" name="books" value="${book.getId()}"/>        
                    <br />
                </c:forEach>
               <input type="submit">
            </div>
        </form>

        <a href="/user/book/">
            My books
        </a>
        
        <div>
            <c:forEach items="${errors}" var="error">
            <b>
                ${error}
            </b> <br/>
            </c:forEach>
        </div>

        <div>
            <b>
                ${message}
            </b>
        </div>
    </body>

</html>