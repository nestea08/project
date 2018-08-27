<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 25.08.2018
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>

    <body>
        <c:choose>
            <c:when test="${empty requestScope.activities}">
                <fmt:message key="possibleActiv.empty"/><br/>
            </c:when>
            <c:otherwise>
                <fmt:message key="possibleActiv.hello"/>:<br/>
            </c:otherwise>
        </c:choose>


        <c:forEach items="${requestScope.activities}" var="activity">
            <ul>
                <li><c:out value="${activity.title}"/></li>
                <li><c:out value="${activity.description}"/></li>
                <a href="${pageContext.request.contextPath}/user/start_activity?id=${activity.id}"> <fmt:message key="possibleActiv.start"/></a>
            </ul>
        </c:forEach>

        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="?page=${i}"><c:out value="${i}"/></a>
        </c:forEach>

        <a href="${pageContext.request.contextPath}/user/user.jsp"><fmt:message key="common.back"/></a>
    </body>
</html>
