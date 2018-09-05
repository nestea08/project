<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
    <div class="index_div">
        <fmt:message key="user.hello"/>:
        <ul class="actions_ul">
            <li class="actions_li">
                <a href="${pageContext.request.contextPath}/admin/requests"><fmt:message key="admin.requests"/></a>
            </li>
            <li class="actions_li">
                <a href="${pageContext.request.contextPath}/admin/history"><fmt:message key="admin.history"/></a>
            </li>
            <li class="actions_li">
                <a href="${pageContext.request.contextPath}/admin/create_activity_page"><fmt:message key="admin.createActivity"/></a>
            </li>
        </ul>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
