<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 22.08.2018
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>
    <body>
        <c:choose>
            <c:when test="${empty requestScope.historyItems}">
                <fmt:message key="history.empty"/><br/>
            </c:when>
            <c:otherwise>
                <fmt:message key="history.message"/>:<br/>
            </c:otherwise>
        </c:choose>

        <c:forEach items="${requestScope.historyItems}" var="item">
            <ul>
                <li><fmt:message key="history.username"/>: <c:out value="${item.tracker.nickname}"/></li>
                <li><fmt:message key="history.title"/>: <c:out value="${item.title}"/></li>
                <li><fmt:message key="history.spentTime"/>: <c:out value="${item.spentTime}"/></li>
                <li><fmt:message key="history.endDate"/>:
                    <fmt:parseDate value="${item.endDate}" pattern="yyyy-MM-dd" var="parsedDate"/>
                    <fmt:formatDate value="${parsedDate}" />
                </li>
            </ul>
        </c:forEach>

        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="?page=${i}"><c:out value="${i}"/></a>
        </c:forEach>

    </body>
</html>