<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
    <div class="index_div">
        <c:choose>
            <c:when test="${empty requestScope.timeTrackings}">
                <fmt:message key="activities.empty"/><br/>
            </c:when>
            <c:otherwise>
                <fmt:message key="activities.hello"/>:<br/>
            </c:otherwise>
        </c:choose>


        <c:forEach items="${requestScope.timeTrackings}" var="timeTracking">
            <ul>
                <li><c:out value="${timeTracking.title}"/></li>
                <li><c:out value="${timeTracking.description}"/></li>
                <li><c:out value="${timeTracking.spentTime}"/> <fmt:message key="activity.hours"/></li>
                <a href="${pageContext.request.contextPath}/user/activity?id=${timeTracking.id}"> <fmt:message key="activities.more"/></a>
                <a href="${pageContext.request.contextPath}/user/activity_remove?id=${timeTracking.id}"> <fmt:message key="activities.remove"/></a>
            </ul>
        </c:forEach>

        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="?page=${i}"><c:out value="${i}"/></a>
        </c:forEach>

        <a href="${pageContext.request.contextPath}/user/user_page"><fmt:message key="common.back"/></a>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
