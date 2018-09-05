<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <jsp:include page="../master.jsp" />
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="mybundle"/>
    <div class="content">
        <fmt:message key="user.hello"/><br/>
        <a href="${pageContext.request.contextPath}/user/activities"><fmt:message key="user.activities"/></a><br/>
        <a href="${pageContext.request.contextPath}/user/possible_activities"><fmt:message key="user.startActivity"/></a>
    </div>
    <jsp:include page="../footer.jsp"/>
</html>

