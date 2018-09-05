<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
    <div class="index_div">
        <fmt:message key="user.hello"/>:
        <ul class="actions_ul">
            <li class="actions_li">
                <a href="${pageContext.request.contextPath}/user/activities"><fmt:message key="user.activities"/></a>
            </li>
            <li class="actions_li">
                <a href="${pageContext.request.contextPath}/user/possible_activities"><fmt:message key="user.startActivity"/></a>
            </li>
        </ul>
    </div>
</div>
<jsp:include page="../footer.jsp"/>


