<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>

<jsp:include page="master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<html>
<head>
    <meta http-equiv="Content-Type" >
</head>
<body>


    <fmt:message key="index.hello"/>

    <a href="${pageContext.request.contextPath}/data">Get data</a>
</body>
</html>
