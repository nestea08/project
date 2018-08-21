<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 20.08.2018
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>
<html>
<head>
    <title>Time Tracking</title>
</head>
<body>
    <c:out value="${requestScope.activity.title}"/><br/>
    <c:out value="${requestScope.activity.description}"/><br/>
    <c:out value="${requestScope.timeSpent}"/><br/>

    <form action="" method="post">
        Add spent time on activity<br/>
        <input type="number" name="spentTime"/>
        <input type="submit" value="Change"/>
    </form>
    <a href="${pageContext.request.contextPath}/user/activity_finish?id=${requestScope.activity.id}"> <fmt:message key="activity.finish"/></a>

</body>
</html>
