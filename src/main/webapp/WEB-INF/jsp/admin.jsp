<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>Welcome : ${pageContext.request.userPrincipal.name}
			| <a href="<c:url value="/logout" />" > Logout</a></h2>
	</c:if>

	<sec:authorize access="isAuthenticated()">
		authenticated as
		<sec:authentication property="principal.username" />
	</sec:authorize>


	<!-- <form method="post" th:action="@{/courses}">
		<select class="span3" name="idCategory">

			<option th:value="${currentCategory.id}" th:text="${currentCategory.category}"></option>

			<div th:each="category : ${listCategory}">
				<option th:value="${category.id}" th:text="${category.category}"></option>
			</div>

		</select>

		<button class="btn" type="submit">Apply</button>
	</form> -->

</body>

</html>