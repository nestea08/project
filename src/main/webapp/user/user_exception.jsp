<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 29.08.2018
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>
<body>
    <c:choose>
        <c:when test="${not empty sessionScope.exception}">
            <fmt:message key="${sessionScope.exception}"/>.
        </c:when>
        <c:otherwise>
            <fmt:message key="exception.message"/>.
        </c:otherwise>
    </c:choose>
    <br/>
    <a href="${pageContext.request.contextPath}/user/user.jsp"><fmt:message key="exception.home"/></a>
</body>
</html>
