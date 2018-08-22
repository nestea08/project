<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 11.08.2018
  Time: 15:45
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
<fmt:message key="admin.hello"/>
<a href="${pageContext.request.contextPath}/admin/requests"><fmt:message key="admin.requests"/></a>
<a href="${pageContext.request.contextPath}/admin/history"><fmt:message key="admin.history"/></a>
</body>
</html>
