<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<html>
<head>

</head>
<body>
<fmt:message key="login.message"/>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="email" name="email" ><br/>
    <input type="password" name="password"><br/>
    <input type="submit" value="login"><br/>
</form>
<c:if test="${not empty requestScope.exception}">
    <fmt:message key="${requestScope.exception}"/>
</c:if>
</body>
</html>
