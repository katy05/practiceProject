<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: Подоприго Екатерина
  Date: 22.02.2021
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:url var="welcome" value="/welcome.jhtml"/>
<c:url var="userTable" value="/userTable.jhtml"/>
<c:url var="logout" value="/logout.jhtml"/>
<c:url var="editUser" value="/editUser.jhtml"/>
<c:url var="deleteUser" value="/deleteUser.jhtml"/>
<head>
    <title>Таблица пользователей</title>
    <link href="${pageContext.servletContext.contextPath}/style/userTable.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/style/style.css" rel="stylesheet" type="text/css">


</head>
<body>
<div class="main">

        <myTag:header thisIsHomePage="false" thisIsUserTablePage="selectedMenuItem"/>
    <div class="content">
        <form method="get" action="${editUser}">
            <button type="submit">Добавить пользователя</button>
            <table class="userTable">
                <caption>Список пользователей</caption>
                <thead>
                <tr>
                    <th>Логин</th>
                    <th>Пароль</th>
                    <th>Роль</th>
                    <th>Email</th>
                    <th>День рождения</th>
                    <th></th>
                </tr>
                </thead>


                <tbody>
                <c:forEach var="users" items="${users}">
                    <tr>
                        <td><a href="${editUser}?userId=${users.id}"
                               class="tdLogin">${users.login}</a>
                        </td>
                        <td>${users.password}</td>
                        <td>${users.role}</td>
                        <td>${users.email}</td>
                        <td>${users.dob}</td>
                        <td>
                            <a href="${deleteUser}?userId=${users.id}"
                               class="tdLogin">
                                <svg height="512pt" viewBox="-47 0 512 512" width="512pt"
                                     xmlns="http://www.w3.org/2000/svg"
                                     class="iconDelete">
                                    <path d="m416.875 114.441406-11.304688-33.886718c-4.304687-12.90625-16.339843-21.578126-29.941406-21.578126h-95.011718v-30.933593c0-15.460938-12.570313-28.042969-28.027344-28.042969h-87.007813c-15.453125 0-28.027343 12.582031-28.027343 28.042969v30.933593h-95.007813c-13.605469 0-25.640625 8.671876-29.945313 21.578126l-11.304687 33.886718c-2.574219 7.714844-1.2695312 16.257813 3.484375 22.855469 4.753906 6.597656 12.445312 10.539063 20.578125 10.539063h11.816406l26.007813 321.605468c1.933594 23.863282 22.183594 42.558594 46.109375 42.558594h204.863281c23.921875 0 44.175781-18.695312 46.105469-42.5625l26.007812-321.601562h6.542969c8.132812 0 15.824219-3.941407 20.578125-10.535157 4.753906-6.597656 6.058594-15.144531 3.484375-22.859375zm-249.320312-84.441406h83.0625v28.976562h-83.0625zm162.804687 437.019531c-.679687 8.402344-7.796875 14.980469-16.203125 14.980469h-204.863281c-8.40625 0-15.523438-6.578125-16.203125-14.980469l-25.816406-319.183593h288.898437zm-298.566406-349.183593 9.269531-27.789063c.210938-.640625.808594-1.070313 1.484375-1.070313h333.082031c.675782 0 1.269532.429688 1.484375 1.070313l9.269531 27.789063zm0 0"/>
                                    <path d="m282.515625 465.957031c.265625.015625.527344.019531.792969.019531 7.925781 0 14.550781-6.210937 14.964844-14.21875l14.085937-270.398437c.429687-8.273437-5.929687-15.332031-14.199219-15.761719-8.292968-.441406-15.328125 5.925782-15.761718 14.199219l-14.082032 270.398437c-.429687 8.273438 5.925782 15.332032 14.199219 15.761719zm0 0"/>
                                    <path d="m120.566406 451.792969c.4375 7.996093 7.054688 14.183593 14.964844 14.183593.273438 0 .554688-.007812.832031-.023437 8.269531-.449219 14.609375-7.519531 14.160157-15.792969l-14.753907-270.398437c-.449219-8.273438-7.519531-14.613281-15.792969-14.160157-8.269531.449219-14.609374 7.519532-14.160156 15.792969zm0 0"/>
                                    <path d="m209.253906 465.976562c8.285156 0 15-6.714843 15-15v-270.398437c0-8.285156-6.714844-15-15-15s-15 6.714844-15 15v270.398437c0 8.285157 6.714844 15 15 15zm0 0"/>
                                </svg>
                            </a>
                        </td>
                    </tr

                </c:forEach>

                </tbody>

            </table>
        </form>

    </div>
    <myTag:footer/>

</div>

</body>
</html>

