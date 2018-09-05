<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<jsp:include page="WEB-INF/master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>



<div class="content">
    <div class="index_div">
        <fmt:message key="index.hello"/>
    </div>
</div>
<jsp:include page="WEB-INF/footer.jsp"/>