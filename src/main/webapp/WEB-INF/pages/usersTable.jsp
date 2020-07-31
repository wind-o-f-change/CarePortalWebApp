<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<a href="/admin"><h2>Личный кабинет</h2></a>
<table>
    <tr>
        <th><h3>Ф.И.О</h3></th>
        <th><h3>Активирован</h3></th>
        <th><h3>email</h3></th>
        <th><h3>Пол</h3></th>
        <th><h3>Роль</h3></th>
        <th><h3>Дата создания</h3></th>
        <c:forEach var="admin" items="${list_users}">
            <tr>
                <th><h4><c:out value="${admin.getFullName()}"/></h4></th>
                <th><h4><c:out value="${admin.isEnabled()}"/></h4></th>
                <th><h4><c:out value="${admin.getEmail()}"/></h4></th>
                <th><h4><c:out value="${admin.getSex()}"/></h4></th>
                <th><h4><c:out value="${admin.getRole()}"/></h4></th>
                <th><h4><c:out value="${admin.getCreated()}"/></h4></th>
            </tr>
        </c:forEach>
    </tr>
</table>
<br><br><br>