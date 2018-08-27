<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>

<jsp:include page="master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<html>
<head>
    <meta http-equiv="Content-Type" >
</head>
<body>
    <fmt:message key="index.hello"/>
    <c:if test="${sessionScope.role eq 'USER'}">
        <a href="${pageContext.request.contextPath}/user/user.jsp"><fmt:message key="index.user"/></a>
    </c:if>
    <c:if test="${sessionScope.role eq 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/admin/admin.jsp"><fmt:message key="index.admin"/></a>
    </c:if>
</body>
</html>
