<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Подоприго Екатерина
  Date: 16.02.2021
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="auth" value="/auth.jhtml"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang.messages"/>
<html>
<head>
    <title><fmt:message key="label.welcome"/></title>
    <c:url var="style" value="/"/>
    <link href="${style}style/login.css" rel="stylesheet" type="text/css">
    <link href="${style}style/style.css" rel="stylesheet" type="text/css">
    <%--    <c:if test="${authFailed}">--%>
    <link href="${pageContext.servletContext.contextPath}/style/failedField.css" rel="stylesheet" type="text/css">
    <%--    </c:if>--%>
</head>
<fmt:message key="label.login" var="i18nLogin"/>
<fmt:message key="label.password" var="i18nPassword"/>
<body>
<div class="session">
    <div class="left">
        <svg enable-background="new 0 0 300 302.5" version="1.1" viewBox="0 0 300 302.5" xml:space="preserve"
             xmlns="http://www.w3.org/2000/svg">
        </svg>
    </div>


    <form:form method="post" action="${auth}" class="log-in" autocomplete="off" modelAttribute="incomingUser">
        <h4><fmt:message key="label.welcome"/></h4>
        <div class="floating-label">
            <form:input name="login" placeholder="${i18nLogin}" id="login" autocomplete="off" value="${failedLogin}"
                        path="login" cssErrorClass="inputFailed"/>
            <label for="login"><fmt:message key="label.login"/>: </label>
            <form:errors path="login" cssClass="failedText"/>

            <div class="icon">
                <?xml version="1.0" encoding="UTF-8"?>
                <svg enable-background="new 0 0 100 100" version="1.1" viewBox="0 0 100 100" xml:space="preserve"
                     xmlns="http://www.w3.org/2000/svg">
<style type="text/css">
    .st0 {
        fill: none;
    }
</style>
                    <g transform="translate(0 -952.36)">
                        <path d="m17.5 977c-1.3 0-2.4 1.1-2.4 2.4v45.9c0 1.3 1.1 2.4 2.4 2.4h64.9c1.3 0 2.4-1.1 2.4-2.4v-45.9c0-1.3-1.1-2.4-2.4-2.4h-64.9zm2.4 4.8h60.2v1.2l-30.1 22-30.1-22v-1.2zm0 7l28.7 21c0.8 0.6 2 0.6 2.8 0l28.7-21v34.1h-60.2v-34.1z"/>
                    </g>
                    <rect class="st0" width="100" height="100"/>
</svg>

            </div>
        </div>
        <div class="floating-label">
            <form:input placeholder="${i18nPassword}" type="password" name="password" id="password" autocomplete="off"
                        path="password" cssErrorClass="inputFailed"/>
            <label for="password"><fmt:message key="label.password"/>:</label>
            <form:errors path="password" cssClass="failedText"/>

            <div class="icon">

                <?xml version="1.0" encoding="UTF-8"?>
                <svg enable-background="new 0 0 24 24" version="1.1" viewBox="0 0 24 24" xml:space="preserve"
                     xmlns="http://www.w3.org/2000/svg">
                    <rect class="st0" width="24" height="24"/>
                    <path class="st1" d="M19,21H5V9h14V21z M6,20h12V10H6V20z"/>
                    <path class="st1"
                          d="M16.5,10h-1V7c0-1.9-1.6-3.5-3.5-3.5S8.5,5.1,8.5,7v3h-1V7c0-2.5,2-4.5,4.5-4.5s4.5,2,4.5,4.5V10z"/>
                    <path class="st1"
                          d="m12 16.5c-0.8 0-1.5-0.7-1.5-1.5s0.7-1.5 1.5-1.5 1.5 0.7 1.5 1.5-0.7 1.5-1.5 1.5zm0-2c-0.3 0-0.5 0.2-0.5 0.5s0.2 0.5 0.5 0.5 0.5-0.2 0.5-0.5-0.2-0.5-0.5-0.5z"/>
</svg>
            </div>

        </div>
        <%--        <div style="display: none" class="failedText">--%>
        <%--            Неправильный логин или пароль--%>
        <%--        </div>--%>
        <button type="submit"><fmt:message key="label.sign.in"/></button>
    </form:form>


</div>

<div class="languages" style="position: absolute">
    <a href="?lang=ru"><fmt:message key="label.lang.ru"/> </a>
    <a href="?lang=en"><fmt:message key="label.lang.en"/> </a>
</div>
</body>

</html>
