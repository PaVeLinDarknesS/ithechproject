<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="utf-8">
    <title>Success</title>
</head>

<body>
        <jsp:include page="../parts/header.jsp" flush="true"/>
    <h2>
        ${message}
    </h2>
    <a href="/book/">All Book</a>
    <jsp:include page="../parts/footer.jsp" flush="true"/>
</body>
</html>