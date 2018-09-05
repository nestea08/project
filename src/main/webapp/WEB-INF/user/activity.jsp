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

<div class="content">
    <div class="index_div">
        <div class="info_div"><fmt:message key="activity.title"/>: <c:out value="${requestScope.timeTracking.title}"/></div>
        <div class="info_div"><fmt:message key="activity.description"/>: <c:out value="${requestScope.timeTracking.description}"/></div>
        <div class="info_div"><fmt:message key="activity.spentTime"/>: <c:out value="${requestScope.timeTracking.spentTime}"/>
            <fmt:message key="activity.hours"/></div>

        <form action="" method="post">
            <div class="info_div">
                <fmt:message key="activity.addSpentTime"/>
            </div>
            <div class="time_add_div">
                <input type="number" name="spentTime"/>
                <input type="submit" value="<fmt:message key="activity.submit"/>"/>
            </div>
        </form>
        <div class="login_exception">
            <c:if test="${not empty requestScope.exception}">
                <fmt:message key="${requestScope.exception}"/><br/>
            </c:if>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/user/activity_finish?id=${requestScope.timeTracking.id}"> <fmt:message key="activity.finish"/></a>
        </div>
        <div class="info_div">
            <a href="${pageContext.request.contextPath}/user/activities"><fmt:message key="common.back"/></a>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
