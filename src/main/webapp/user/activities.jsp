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
    <c:choose>
        <c:when test="${empty requestScope.trackedItems}">
            <fmt:message key="activities.empty"/><br/>
        </c:when>
        <c:otherwise>
            <fmt:message key="activities.hello"/>:<br/>
        </c:otherwise>
    </c:choose>


    <c:forEach items="${requestScope.trackedItems}" var="trackedItem">
        <ul>
            <li><c:out value="${trackedItem.title}"/></li>
            <li><c:out value="${trackedItem.description}"/></li>
            <li><c:out value="${trackedItem.spentTime}"/> <fmt:message key="activity.hours"/></li>
            <a href="${pageContext.request.contextPath}/user/activity?id=${trackedItem.id}"> <fmt:message key="activities.more"/></a>
            <a href="${pageContext.request.contextPath}/user/activity_remove?id=${trackedItem.id}"> <fmt:message key="activities.remove"/></a>
        </ul>
    </c:forEach>

    <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
        <a href="?page=${i}"><c:out value="${i}"/></a>
    </c:forEach>

    <a href="${pageContext.request.contextPath}/user/user.jsp"><fmt:message key="common.back"/></a>
</body>
</html>
