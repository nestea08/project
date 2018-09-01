<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 20.08.2018
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Time Tracking</title>
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language.equals("en") ? 'selected' : ''}>English</option>
        <option value="ru" ${language.equals("ru") ? 'selected' : ''}>Русский</option>
    </select>
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="master.login"/></a>
            <a href="${pageContext.request.contextPath}/signUp.jsp"><fmt:message key="master.signUp"/></a>
        </c:when>
        <c:otherwise>
            <fmt:message key="master.hello"/>, <c:out value="${sessionScope.user}"/><a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>
