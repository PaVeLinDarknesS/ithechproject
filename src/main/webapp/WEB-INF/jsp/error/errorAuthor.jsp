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
    <h1>
        Oops!
    </h1>
    <h2>
        ${error}
    </h2>
    <a href="/author/">All Authors</a>
</body>

</html>