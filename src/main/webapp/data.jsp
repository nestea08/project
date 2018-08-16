<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 12.08.2018
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    data

    <c:forEach items="${requestScope.users}" var="user">
        <ul>
            <li><c:out value="${user.login}"/></li>
            <li><c:out value="${user.password}"/></li>
            <li><c:out value="${user.role}"/></li>
        </ul>
    </c:forEach>
    <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
        <a href="?page=${i}"><c:out value="${i}"/></a>
    </c:forEach>

</body>
</html>
