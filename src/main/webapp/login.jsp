<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 11.08.2018
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>
<head>

</head>
<body>
<fmt:message key="output.login"/>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" name="login">
    <input type="password" name="password">
    <input type="submit" value="login">
</form>
</body>
</html>