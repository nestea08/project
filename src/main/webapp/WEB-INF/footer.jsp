<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 03.09.2018
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<footer class="footer">
    <div class="footer_div">
        2018 <fmt:message key="footer.rights"/>
    </div>
</footer>
