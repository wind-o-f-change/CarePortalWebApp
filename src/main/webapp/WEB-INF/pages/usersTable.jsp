<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<table>
    <tr>
        <th><h5>Id</h5></th>
        <th><h5>Ф.И.О</h5></th>
        <th><h5>Активирован</h5></th>
        <th><h5>email</h5></th>
        <th><h5>Пол</h5></th>
        <th><h5>Роль</h5></th>
        <th><h5>Дата создания</h5></th>
        <c:forEach var="admin" items="${list_users}">
    <tr>
        <th><h5><c:out value="${admin.getId()}"/></h5></th>
        <th><h5><c:out value="${admin.getFullName()}"/></h5></th>
        <th><h5><c:out value="${admin.isEnabled()}"/></h5></th>
        <th><h5><c:out value="${admin.getEmail()}"/></h5></th>
        <th><h5><c:out value="${admin.getSex()}"/></h5></th>
        <th><h5><c:out value="${admin.getRole()}"/></h5></th>
        <th><h5><c:out value="${admin.getCreated()}"/></h5></th>
    </tr>
    </c:forEach>
    </tr>
</table>
<br><br><br>