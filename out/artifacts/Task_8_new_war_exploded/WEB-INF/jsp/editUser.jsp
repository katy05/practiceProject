<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<html>
<head>
    <link href="${pageContext.servletContext.contextPath}/style/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/style/style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/style/editUser.css" rel="stylesheet" type="text/css">
    <c:if test="${param.editFailed}">
        <link href="${pageContext.servletContext.contextPath}/style/failedField.css" rel="stylesheet" type="text/css">
    </c:if>

    <title>Редактирование пользователя</title>
    <style>
        input:invalid {
            border-bottom: 1px solid red;
        }

    </style>
</head>
<body>
<div class="main">
    <myTag:header thisIsHomePage="false" thisIsUserTablePage="selectedMenuItem"/>
    <div class="content">
        <form method="post" action="${editUser}">
            <div class="floating-label <c:if test="${failedLogin}"> failedEdit</c:if>">

                <input name="newLogin" placeholder="Логин" id="login" autocomplete="off"
                       value="${user.login}">
                <label for="login">Логин:</label>
                <div style="display: none" <c:if test="${failedLogin}">class="failedText"</c:if>>
                    Неправильный логин
                </div>
            </div>
            <div class="floating-label <c:if test="${failedPassword}"> failedEdit</c:if>">

                <input name="newPassword" placeholder="Пароль" id="password" autocomplete="off"
                       value="${user.password}" type="password">
                <label for="password">Пароль:</label>
                <div style="display: none" <c:if test="${failedPassword}">class="failedText"</c:if>>
                    Неправильный пароль
                </div>

            </div>
            <div class="select <c:if test="${failedRole}"> failedEdit</c:if>">
                <label for="role">Роль:</label>
                <select name="newRole" id="role">
                    <option disabled selected>Выберите роль:</option>

                    <option
                            <c:if test="${user.role == 'root'}">selected</c:if> value="root">root
                    </option>
                    <option
                           <c:if test="${user.role == 'user' || user.role == null}">selected</c:if> value="user">user
                    </option>
                </select>
                <div style="display: none; margin-left: 0" <c:if test="${failedRole}">class="failedText"</c:if>>
                    У пользователя должна быть роль
                </div>

            </div>
            <div class="floating-label <c:if test="${failedEmail}"> failedEdit</c:if>">

                <input name="newEmail" placeholder="Email" id="email" autocomplete="off"
                       value="${user.email}">
                <label for="email">Email:</label>
                <div style="display: none" <c:if test="${failedEmail}">class="failedText"</c:if>>
                    Неправильный email
                </div>

            </div>
            <div class="floating-label <c:if test="${failedDob}"> failedEdit</c:if>">

                <input name="newDob" placeholder="День рождения" id="dob" autocomplete="off"
                       value="${user.dob}" type="date">
                <label for="dob"
                       <c:if test="${failedDob}">style="top: calc(50% - 30px);"</c:if>
                >День рождения:</label>
                <div style="display: none" <c:if test="${failedDob}">class="failedText"</c:if>>
                    День рождения не может быть пустым
                </div>

            </div>

            <button type="submit" name="idEditedUser" value="${userId}">
                <c:if test="${thisIsPageAdd}">Добавить</c:if>
                <c:if test="${thisIsPageEdit}">Изменить</c:if>
            </button>
        </form>
    </div>

<%--    <jsp:include page="footer.jsp"/>--%>
<myTag:footer/>
</div>

</body>
</html>
