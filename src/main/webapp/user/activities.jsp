<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 20.08.2018
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<html>
<head>
    <title>Time Tracking</title>
</head>
<body>
    <fmt:message key="activities.hello"/>:<br/>

    <form action="" method="post">
    <c:forEach items="${requestScope.activities}" var="map">
        <ul>
            <li><c:out value="${map.key.title}"/></li>
            <li><c:out value="${map.key.description}"/></li>
            <li><c:out value="${map.value}"/></li>
            <a href="${pageContext.request.contextPath}/user/activity?id=${map.key.id}"> <fmt:message key="activities.more"/></a>
            <a href="${pageContext.request.contextPath}/user/activity_remove?id=${map.key.id}"> <fmt:message key="activities.remove"/></a>
        </ul>
    </c:forEach>
    </form>
    <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
        <a href="?page=${i}"><c:out value="${i}"/></a>
    </c:forEach>
</body>
</html>
