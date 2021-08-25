<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<html>
<head>
    <link href="${style}/login.css" rel="stylesheet" type="text/css">
    <link href="${style}/style.css" rel="stylesheet" type="text/css">
    <link href="${style}/editUser.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/style/failedField.css" rel="stylesheet" type="text/css">


    <title>Редактирование пользователя</title>
</head>
<body>
<div class="main">
    <myTag:header
            thisIsHomePage="false"
            thisIsUserTablePage="selectedMenuItem"
            roles="${roles}"
    />
    <div class="content">
        <form:form method="post" action="${editUser}" modelAttribute="user">
            <div class="floating-label <c:if test="${failedLogin}"> failedEdit</c:if>">

                <form:input placeholder="Логин" value="${user.login}" path="login" cssErrorClass="inputFailed"/>
                <label for="login">Логин:</label>
                <form:errors path="login" cssClass="failedText"/>

            </div>
            <div class="floating-label <c:if test="${failedPassword}"> failedEdit</c:if>">

                <form:input path="password" placeholder="Пароль" value="${password}" type="password"
                            cssErrorClass="inputFailed"/>
                <label for="password">Пароль:</label>
                <form:errors path="password" cssClass="failedText"/>


            </div>
            <div class="select">
                <form:checkboxes items="${allRoles}" path="roles" itemLabel="name" itemValue="name"
                                 cssErrorClass="checkbox failedRoles" cssClass="checkbox"/>

                <form:errors path="roles" cssClass="failedText"/>

            </div>

            <div class="floating-label <c:if test="${failedEmail}"> failedEdit</c:if>">

                <form:input path="email" placeholder="Email" value="${email}" cssErrorClass="inputFailed"/>
                <label for="email">Email:</label>
                <form:errors path="email" cssClass="failedText"/>
                    <%--        <div style="display: none" <c:if test="${failedEmail}">class="failedText"</c:if>>--%>
                    <%--            Неправильный email--%>
                    <%--        </div>--%>

            </div>
            <div class="floating-label <c:if test="${failedDob}"> failedEdit</c:if>">

                <form:input name="newDob" path="dob" placeholder="День рождения" id="dob" autocomplete="off"
                            value="${user.dob}" type="date" cssErrorClass="inputFailed"/>
                <label for="dob"
                       <c:if test="${failedDob}">style="top: calc(50% - 30px);"</c:if>
                >День рождения:</label>
                <form:errors path="dob" cssClass="failedText"/>


            </div>

            <form:button type="submit" name="id" value="${user.id}" path="id">
                <c:if test="${thisIsPageAdd}">Добавить</c:if>
                <c:if test="${thisIsPageEdit}">Изменить</c:if>
            </form:button>
        </form:form>
    </div>

    <myTag:footer/>
</div>

</body>
</html>
