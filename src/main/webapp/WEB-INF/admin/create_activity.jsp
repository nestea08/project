<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
    <fmt:message key="createActivity.message"/>
    <form method="post" action="${pageContext.request.contextPath}/admin/create_activity">
        <label><fmt:message key="createActivity.enTitle"/>: <input type="text" name="enTitle" ></label>
        <label><fmt:message key="createActivity.ruTitle"/>: <input type="text" name="ruTitle" ></label><br/>
        <label><fmt:message key="createActivity.enDescription"/>: <textarea name="enDescription"></textarea></label>
        <label><fmt:message key="createActivity.ruDescription"/>: <textarea name="ruDescription"></textarea></label><br/>
        <input type="submit" value="Create">
    </form>
    <c:if test="${not empty requestScope.exception}">
        <fmt:message key="${requestScope.exception}"/><br/>
    </c:if>
    <a href="${pageContext.request.contextPath}/admin/admin_page"><fmt:message key="common.back"/></a>
</div>
<jsp:include page="../footer.jsp"/>
