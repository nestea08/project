<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
<fmt:message key="admin.hello"/><br/>
    <a href="${pageContext.request.contextPath}/admin/requests"><fmt:message key="admin.requests"/></a><br/>
    <a href="${pageContext.request.contextPath}/admin/history"><fmt:message key="admin.history"/></a><br/>
    <a href="${pageContext.request.contextPath}/admin/create_activity_page"><fmt:message key="admin.createActivity"/></a>
</div>
<jsp:include page="../footer.jsp"/>
