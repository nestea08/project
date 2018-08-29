<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 28.08.2018
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>
    <fmt:message key="createActivity.message"/>
    <form method="post" action="${pageContext.request.contextPath}/admin/create_activity">
        <label><fmt:message key="activity.title"/>: <input type="text" name="title"></label><br/>
        <label><fmt:message key="activity.description"/>: <textarea name="description"></textarea></label><br/>
        <input type="submit" value="Create">
    </form>
    <c:if test="${not empty requestScope.exception}">
        <fmt:message key="${requestScope.exception}"/><br/>
    </c:if>
    <a href="${pageContext.request.contextPath}/admin/admin.jsp"><fmt:message key="common.back"/></a>
<body>

</body>
</html>
