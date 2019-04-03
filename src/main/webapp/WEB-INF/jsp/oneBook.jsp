
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>
<c:set var="author" value="${book.get().getAuthor()}" />

<html lang="en">
    <head>
        <title>All Books</title>
    </head>
    <body>
        <div>
            ${book.get().getTitle()}, 
            ${book.get().getYear()}, count = 
            ${book.get().getCount()}
            <hr />
            ${author.getFirstName()} <br />
            ${author.getLastName()}
        </div>
    </body>

</html>