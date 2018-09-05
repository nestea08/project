<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
    <div class="index_div">
        <c:choose>
            <c:when test="${not empty sessionScope.exception}">
                <fmt:message key="${sessionScope.exception}"/>.
            </c:when>
            <c:otherwise>
                <fmt:message key="exception.message"/>.
            </c:otherwise>
        </c:choose>
        <br/>
        <a href="${pageContext.request.contextPath}/admin/admin_page"><fmt:message key="exception.home"/></a>
    </div>
</div>
<jsp:include page="../footer.jsp"/>