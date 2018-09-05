<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../master.jsp" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="mybundle"/>

<div class="content">
    <div class="login_div_holder">
        <div class="login_div">
            <div class="login_message">
                <fmt:message key="signUp.message"/>
            </div>
            <form class="login_form" method="post" action="${pageContext.request.contextPath}/guest/sign_up">
                <input class="login_input" type="text" name="nickname" placeholder="<fmt:message key="signUp.nickname"/>"/>
                <input class="login_input" type="email" name="email" placeholder="<fmt:message key="signUp.email"/>"/>
                <input class="login_input" type="password" name="password" placeholder="<fmt:message key="signUp.password"/>"/>
                <input class="login_submit signUp_submit" type="submit" value="<fmt:message key="signUp.submit"/>">
            </form>
            <c:if test="${not empty requestScope.exception}">
                <div class="login_exception">
                    <fmt:message key="${requestScope.exception}"/>
                </div>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>

