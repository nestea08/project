<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 27.08.2018
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>

<body>
    <fmt:message key="activityStarted.message"/>
    <a href="${pageContext.request.contextPath}/user/user.jsp"><fmt:message key="common.back"/></a>
</body>
</html>
