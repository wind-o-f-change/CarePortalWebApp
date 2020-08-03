<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${list_users.size() > 0}">
    <c:set var="isEnabled" value="${admin.isEnabled()}"/>

<table>
    <tr>
        <th><h5>Id</h5></th>
        <th><h5>Ф.И.О</h5></th>
        <th><h5>Активирован</h5></th>
        <th><h5>email</h5></th>
        <th><h5>Пол</h5></th>
        <th><h5>Роль</h5></th>
        <th><h5>Дата создания</h5></th>
<%--        <th><h5>Профиль</h5></th>--%>

        <c:forEach var="admin" items="${list_users}">

            <tr>
                <th><h5><c:out value="${admin.getId()}"/></h5></th>
                <th><h5><c:out value="${admin.getFullName()}"/></h5></th>
                <th>
                    <c:if test="${admin.isEnabled()}">
                        <input type="checkbox" checked disabled/>
                    </c:if>
                    <c:if test="${!admin.isEnabled()}">
                        <input type="checkbox" disabled/>
                    </c:if>
                </th>
                <th><h5><c:out value="${admin.getEmail()}"/></h5></th>
                <th><h5><c:out value="${admin.getSex()}"/></h5></th>
                <th><h5><c:out value="${admin.getRole()}"/></h5></th>
                <th><h5><c:out value="${admin.getCreated()}"/></h5></th>
<%-- // добавить ссылку на страницу детализации юзера(не на личную) по типу как у ксении--%>
            </tr>
        </c:forEach>
    </tr>

</table>

</c:if>
<c:if test="${list_users.size() < 1}">
    <h3><b>По заданным параметрам никого не найдено!</b></h3>
</c:if>
<br><br><br>
