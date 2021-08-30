<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%--
  Created by IntelliJ IDEA.
  User: Подоприго Екатерина
  Date: 18.02.2021
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="welcome" value="/welcome.jhtml"/>
<c:url var="userTable" value="/userTable.jhtml"/>
<c:url var="logout" value="/logout.jhtml"/>
<c:url var="editUser" value="/editUser.jhtml"/>
<c:url var="style" value="/style"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang.messages"/>
<html>
<head>
    <link href="${style}/login.css" rel="stylesheet" type="text/css">
    <link href="${style}/style.css" rel="stylesheet" type="text/css">
    <link href="${style}/editUser.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/style/failedField.css" rel="stylesheet" type="text/css">


    <title><spring:message code="label.user.editing"/></title>
</head>
<spring:message code="label.login" var="i18nLogin"/>
<spring:message code="label.password" var="i18nPassword"/>
<spring:message code="label.email" var="i18nEmail"/>
<spring:message code="label.dob" var="i18nDob"/>
<body>
<div class="main">
    <sec:authentication property="principal" var="authUser"/>

    <myTag:header
            thisIsHomePage="false"
            thisIsUserTablePage="selectedMenuItem"
            roles="${authUser.authorities}"
    />
    <div class="content">
        <form:form method="post" action="${editUser}" modelAttribute="user">
            <div class="floating-label <c:if test="${failedLogin}"> failedEdit</c:if>">

                <form:input placeholder="${i18nLogin}" value="${user.login}" path="login" cssErrorClass="inputFailed"/>
                <label for="login"><spring:message code="label.login"/>:</label>
                <form:errors path="login" cssClass="failedText"/>

            </div>
            <c:if test="${thisIsPageAdd}">
            <div class="floating-label <c:if test="${failedPassword}"> failedEdit</c:if>">

                <form:input path="password" placeholder="${i18nPassword}" value="${password}" type="password"
                            cssErrorClass="inputFailed"/>
                <label for="password"><spring:message code="label.password"/>:</label>
                <form:errors path="password" cssClass="failedText"/>


            </div>
            </c:if>
            <div class="select">
                <form:checkboxes items="${allRoles}" path="roles" itemLabel="name" itemValue="name"
                                 cssErrorClass="checkbox failedRoles" cssClass="checkbox"/>

                <form:errors path="roles" cssClass="failedText"/>

            </div>

            <div class="floating-label <c:if test="${failedEmail}"> failedEdit</c:if>">

                <form:input path="email" placeholder="${i18nEmail}" value="${email}" cssErrorClass="inputFailed"/>
                <label for="email"><spring:message code="label.email"/>:</label>
                <form:errors path="email" cssClass="failedText"/>
                    <%--        <div style="display: none" <c:if test="${failedEmail}">class="failedText"</c:if>>--%>
                    <%--            Неправильный email--%>
                    <%--        </div>--%>

            </div>
            <div class="floating-label <c:if test="${failedDob}"> failedEdit</c:if>">

                <form:input name="newDob" path="dob" placeholder="${i18nDob}" id="dob" autocomplete="off"
                            value="${user.dob}" type="date" cssErrorClass="inputFailed"/>
                <label for="dob"
                       <c:if test="${failedDob}">style="top: calc(50% - 30px);"</c:if>
                ><spring:message code="label.dob"/>:</label>
                <form:errors path="dob" cssClass="failedText"/>


            </div>

            <form:button type="submit" name="id" value="${user.id}" path="id">
                <c:if test="${thisIsPageAdd}"><spring:message code="label.add"/></c:if>
                <c:if test="${thisIsPageEdit}"><spring:message code="label.update"/></c:if>
            </form:button>
        </form:form>
    </div>

    <myTag:footer/>
</div>

</body>
</html>
