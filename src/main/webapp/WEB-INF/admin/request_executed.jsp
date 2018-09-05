<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
    <div class="index_div">
        <fmt:message key="request_executed.message"/>
        <a href="${pageContext.request.contextPath}/admin/requests"><fmt:message key="common.back"/></a>
    </div>
</div>
<jsp:include page="../footer.jsp"/>