<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 11.08.2018
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<html>
    <body>
        <fmt:message key="exception.message"/><br/>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="exception.home"/></a>
    </body>
</html>
