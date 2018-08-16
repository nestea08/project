<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<html>
<head>
    <meta http-equiv="Content-Type" >
</head>
<body>
    <form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language.equals("en") ? 'selected' : ''}>English</option>
        <option value="ru" ${language.equals("ru") ? 'selected' : ''}>Russian</option>
    </select>
    </form>
    <fmt:message key="output.hello"/>

    <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
    <a href="${pageContext.request.contextPath}/data">Login</a>
</body>
</html>
