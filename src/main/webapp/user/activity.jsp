<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 20.08.2018
  Time: 16:46
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
    <label><fmt:message key="activity.title"/>: <c:out value="${requestScope.timeTracking.title}"/></label><br/>
    <label><fmt:message key="activity.description"/>: <c:out value="${requestScope.timeTracking.description}"/></label><br/>
    <label><fmt:message key="activity.spentTime"/>: <c:out value="${requestScope.timeTracking.spentTime}"/>
        <fmt:message key="activity.hours"/></label><br/>

    <form action="" method="post">
        <fmt:message key="activity.addSpentTime"/>
        <input type="number" name="spentTime"/>
        <input type="submit" value="Change"/>
    </form>

    <c:if test="${not empty requestScope.exception}">
        <fmt:message key="${requestScope.exception}"/><br/>
    </c:if>

    <a href="${pageContext.request.contextPath}/user/activity_finish?id=${requestScope.timeTracking.id}"> <fmt:message key="activity.finish"/></a><br/>
    <a href="${pageContext.request.contextPath}/user/activities"><fmt:message key="common.back"/></a>
</body>
</html>
