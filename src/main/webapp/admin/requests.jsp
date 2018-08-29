<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 22.08.2018
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>
<body>
    <c:choose>
        <c:when test="${empty requestScope.requests}">
            <fmt:message key="requests.empty"/>:<br/>
        </c:when>
        <c:otherwise>
            <fmt:message key="requests.message"/>:<br/>
        </c:otherwise>
    </c:choose>
    <c:forEach items="${requestScope.requests}" var="request">
        <ul>
            <li><fmt:message key="requests.username"/>: <c:out value="${request.tracker.nickname}"/></li>
            <li><fmt:message key="requests.activity"/>: <c:out value="${request.activity.title}"/></li>
            <li><fmt:message key="requests.type"/>:
                <c:choose>
                    <c:when test="${request.type eq 'REMOVE'}">
                        <fmt:message key="requests.type.remove"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="requests.type.add"/>
                    </c:otherwise>
                </c:choose>
            </li>
            <a href="${pageContext.request.contextPath}/admin/execute_request?id=${request.id}"><fmt:message key="requests.execute"/></a>
            <a href="${pageContext.request.contextPath}/admin/refuse_request?id=${request.id}"><fmt:message key="requests.refuse"/></a>
        </ul>
    </c:forEach>
    <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
        <a href="?page=${i}"><c:out value="${i}"/></a>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/admin/admin.jsp"><fmt:message key="common.back"/></a>
</body>
</html>
