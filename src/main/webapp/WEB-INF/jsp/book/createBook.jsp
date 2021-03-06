<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.dto.*" %>
<c:set var="authorId" value="${book.getAuthorId()}" />

<html lang="en">

<head>
    <title>Create Book</title>

    <script type="text/javascript">
        function confirmCreate() {
            var message = document.getElementsById('title').value;
            return confirm("Do you want save book '"+message+"'?");
        }
    </script>
</head>

<body>
    <div>
            <jsp:include page="../parts/header.jsp" flush="true"/>
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
        <form action="/book/create" method="POST">
        
            <p><b>Title:</b><br />
                <input name="title" value="${book.getTitle()}"/>
            </p>
            
            <p><b>Year:</b><br />
                <input name="year" value="${book.getYear()}"/>
            </p>

            <p><b>Count:</b><br />
                <input name="count" type="number" value="${book.getCount()}"/>
            </p>

            <p>
                <b>Author:</b> <br />
                <select name="authorId">
                    <option value="0" selected>none</option>
                    <c:forEach items="${authors}" var="author">
                        <c:choose>
                            <c:when test="${author.getId().equals(authorId)}">
                                <option value="${author.getId()}" selected>${author.toString()}</option>
                            </c:when>    
                            <c:otherwise>
                                <option value="${author.getId()}">${author.toString()}</option>   
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>

            <hr />

            <input type="submit" name="button" value="Create" onclick="return confirmCreate()"> 
        
        </form>
        <a href="/book/">All Books</a>
    </div>
    <jsp:include page="../parts/footer.jsp" flush="true"/>
</body>
</html>