<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
    <div class="index_div">
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

        <a href="${pageContext.request.contextPath}/user/user_page"><fmt:message key="common.back"/></a>
    </div>
</div>
<jsp:include page="../footer.jsp"/>

