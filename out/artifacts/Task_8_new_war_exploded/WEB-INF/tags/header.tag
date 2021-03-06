<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="thisIsHomePage" required="true" %>
<%@ attribute name="thisIsUserTablePage" required="true" %>
<c:url var="welcome" value="/welcome.jhtml"/>
<c:url var="userTable" value="/userTable.jhtml"/>
<c:url var="logout" value="/logout.jhtml"/>

<header>
    <span class="logo">Rina</span>

    <div class="menu">

        <a href="${welcome}" id="<c:out value="${thisIsHomePage}"/>">Главная</a>
        <c:if test="${role == 'root'}">
            <a href="${userTable}" id="<c:out value="${thisIsUserTablePage}"/>">Список пользователей</a>
        </c:if>
    </div>

    <form method="get" action="${logout}" style="padding: 0">
        <button type="submit" class="buttonLogout">Выйти</button>
    </form>
</header>

