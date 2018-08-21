<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 20.08.2018
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>
<head>
</head>
<body>
    <fmt:message key="remove.message"/>.
    <a href="${pageContext.request.contextPath}/user/activities"><fmt:message key="common.back"/></a>
</body>
</html>
