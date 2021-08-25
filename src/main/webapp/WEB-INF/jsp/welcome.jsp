<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang.messages"/>
<head>
    <title><fmt:message key="label.main"/></title>
    <link href="${pageContext.servletContext.contextPath}/style/welcome.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/style/style.css" rel="stylesheet" type="text/css">


</head>


<body>

<div class="main">
    <%--    <c:set var="thisIsHomePage" value="selectedMenuItem" scope="request"/>--%>
    <%--    <c:set var="thisIsUserListPage" value="" scope="request"/>--%>
    <%--    <jsp:include page="header.jsp"/>--%>
    <myTag:header
            thisIsHomePage="selectedMenuItem"
            thisIsUserTablePage="false"
            roles="${roles}"
    />

    <div class="content">
        <img src="${pageContext.servletContext.contextPath}/image/pexels-photo-114979.jpeg" alt="Красивая картинка">
        <div class="rectWithText">
            <span>

                ${login},

                    <c:forEach var="role" items="${roles}">${role.name} </c:forEach>
            <fmt:message key="label.welcome.text"/> </span>
        </div>
    </div>

    <myTag:footer/>
</div>

</body>
</html>
