<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: Подоприго Екатерина
  Date: 16.02.2021
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="userTable" value="/userTable.jhtml"/>
<c:url var="logout" value="/logout.jhtml"/>
<html>
<head>
    <title>Главная</title>
    <link href="${pageContext.servletContext.contextPath}/style/welcome.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/style/style.css" rel="stylesheet" type="text/css">


</head>
<body>
<div class="main">
    <%--    <c:set var="thisIsHomePage" value="selectedMenuItem" scope="request"/>--%>
    <%--    <c:set var="thisIsUserListPage" value="" scope="request"/>--%>
    <%--    <jsp:include page="header.jsp"/>--%>
    <myTag:header thisIsHomePage="selectedMenuItem" thisIsUserTablePage="false"/>

        <div class="content">
            <img src="${pageContext.servletContext.contextPath}/image/pexels-photo-114979.jpeg" alt="Красивая картинка">
            <div class="rectWithText">
            <span>
                ${login}, добро пожаловать.
            </span>
                <span>Сейчас ты находишься на самой безполезной странице с красивой картинкой</span>
            </div>
        </div>

<myTag:footer/>
</div>

</body>
</html>
