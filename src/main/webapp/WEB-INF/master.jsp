<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Time Tracking</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"/>
</head>
<body>
    <header>
        <div class="header">
            <div class="language_div">
                <form class="language_form">
                    <select class="language_select" name="language" onchange="submit()">
                        <option class="language_option" value="en" ${language.equals("en") ? 'selected' : ''}>English</option>
                        <option class="language_option" value="ru" ${language.equals("ru") ? 'selected' : ''}>Русский</option>
                    </select>
                </form>
            </div>
            <div class="title">
                Time tracking
            </div>
            <div class="role_refs_div">
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <a class="login_ref" href="${pageContext.request.contextPath}/guest/login_page"><fmt:message key="master.login"/></a>
                        <a href="${pageContext.request.contextPath}/guest/sign_up_page"><fmt:message key="master.signUp"/></a>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="master.hello"/>, <c:out value="${sessionScope.user}"/>
                        <a href="${pageContext.request.contextPath}/guest/logout">Logout</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </header>
</body>
</html>
