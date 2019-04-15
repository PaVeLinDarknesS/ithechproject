<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>
<c:set var="author" value="${book.getAuthor()}" />

<html lang="en">

<head>
    <title>${book.getTitle()}</title>

    <script type="text/javascript">
        function confirmUpdate() {
            return confirm("Do you want update book '${book.getTitle()}'?");
        }
    </script>
</head>

<body>
        <jsp:include page="../parts/header.jsp" flush="true"/>
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

            <p>
                <b>Author:</b> <br />
                <select name="authorId">
                    <option value="0" selected>none</option>
                    <c:forEach items="${authors}" var="oneAuthor">
                        <c:choose>
                            <c:when test="${oneAuthor.equals(author)}">
                                <option value="${oneAuthor.getId()}" selected>${oneAuthor.toString()}</option>
                            </c:when>    
                            <c:otherwise>
                                <option value="${oneAuthor.getId()}">${oneAuthor.toString()}</option>   
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>

            <hr />
            <input type="submit" name="button" value="Save" onclick="return confirmUpdate()">
        </form>
        <a href="/book/">All Books</a>
    </div>
    <jsp:include page="../parts/footer.jsp" flush="true"/>
</body>
</html>