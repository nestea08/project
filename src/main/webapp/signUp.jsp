<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 22.08.2018
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>

<body>
    <fmt:message key="signUp.message"/>
    <form method="post" action="${pageContext.request.contextPath}/signUp">
        <fmt:message key="signUp.nickname"/>: <input type="text" name="nickname"/><br/>
        <fmt:message key="signUp.email"/>: <input type="email" name="email"/><br/>
        <fmt:message key="signUp.password"/>: <input type="password" name="password"/><br/>
        <input type="submit" value = "Sign up">
    </form>
</body>
</html>
