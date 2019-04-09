
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itech.library.entity.*" %>

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>All Authors</title>
        <link rel="icon" href='<c:url value="/images/favicon.ico" />' type="image/x-icon">
    </head>
    <body>
            <jsp:include page="../parts/header.jsp" flush="true"/>
          
            <a href="/author/create">Add Author</a>
        <div>
            <ol>
                <c:forEach items="${authors}" var="author">
                    <li> 
                        <a href="/author/${author.getId()}">
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
    <jsp:include page="../parts/footer.jsp" flush="true"/>
</html>