<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="utf-8">
    <title>Error</title>
</head>

<body>
        <jsp:include page="../parts/header.jsp" flush="true"/>
    <h1>
        Oops!
    </h1>
    <h2>
        ${error}
    </h2>
    <a href="/book/">All Books</a>
    <jsp:include page="../parts/footer.jsp" flush="true"/>
</body>
</html>