<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
    <div class="index_div">
        <div class="flex-div">
            <div class="login_message">
                <fmt:message key="createActivity.message"/>
            </div>
            <form class="create_act_form" method="post" action="${pageContext.request.contextPath}/admin/create_activity">
                <div><fmt:message key="createActivity.enTitle"/>: <input class="login_input create_act_div" type="text" name="enTitle" ></div>
                <div><fmt:message key="createActivity.ruTitle"/>: <input class="login_input create_act_div" type="text" name="ruTitle" ></div>
                <div class="create_act_flex"><fmt:message key="createActivity.enDescription"/>:<textarea class="create_act_field" name="enDescription"></textarea></div>
                <div class="create_act_flex"><fmt:message key="createActivity.ruDescription"/>:<textarea class="create_act_field" name="ruDescription"></textarea></div><br/>
                <input type="submit" value="<fmt:message key="createActivity.submit"/>">
            </form>
            <div class="login_exception">
                <c:if test="${not empty requestScope.exception}">
                    <fmt:message key="${requestScope.exception}"/><br/>
                </c:if>
            </div>
            <a href="${pageContext.request.contextPath}/admin/admin_page"><fmt:message key="common.back"/></a>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
