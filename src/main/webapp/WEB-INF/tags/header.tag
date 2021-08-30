<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="thisIsHomePage" required="true" %>
<%@ attribute name="thisIsUserTablePage" required="true" %>
<%@ attribute name="roles" required="true" %>
<c:url var="welcome" value="/welcome.jhtml"/>
<c:url var="userTable" value="/userTable.jhtml"/>
<c:url var="logout" value="/logout.jhtml"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang.messages"/>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header>
    <span class="logo">Rina</span>
    <div class="menu">
        <a href="${welcome}" id="<c:out value="${thisIsHomePage}"/>"><spring:message code="label.main"/> </a>
        <c:forEach var="role" items="${roles}">
            <c:if test="${role == '[ROLE_ROOT'}">
                <a href="${userTable}" id="<c:out value="${thisIsUserTablePage}"/>"><spring:message
                        code="label.users.list"/> </a>
            </c:if>
        </c:forEach>
    </div>

    <form method="get" action="/logout" style="padding: 0">
        <button type="submit" class="buttonLogout"><spring:message code="label.sing.up"/></button>
    </form>
</header>

