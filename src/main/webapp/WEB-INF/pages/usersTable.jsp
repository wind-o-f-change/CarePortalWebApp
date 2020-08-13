<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${userChangesDto.getUsers().size() >= 1}">
<form:form action="/admin/saveUsersChanges" method="post" modelAttribute="userChangesDto">
    <table>
        <tr>
            <th>Id</th>
            <th>Ф.И.О</th>
            <th>Активирован
                <input type="checkbox" id="checkAll"/></th>
            <th>Email</th>
            <th>Пол</th>
            <th>Роль</th>
            <th>Дата создания</th>
            <th>Ссылка на профиль</th>
        </tr>
        <c:forEach var="user" items="${userChangesDto.getUsers()}" varStatus="vsq">
        <tr>
            <td>
                <form:hidden path="users[${vsq.index}].id"/>
                <c:out value="${user.getId()}"/></td>
            </td>
            <td>
                <c:out value="${user.getFullName()}"/></td>
            <td>
                <c:if test="${user.isEnabled()}">
                    <form:checkbox  path="users[${vsq.index}].enabled" checked="checked"/>
                </c:if>
                <c:if test="${!user.isEnabled()}">
                    <form:checkbox path="users[${vsq.index}].enabled"/>
                </c:if>
            </td>
            <td>
                <c:out value="${user.getEmail()}"/></td>
            <c:set var="sex" value="${user.sex}"/>
            <c:set var="MAN" value="MAN"/>
            <c:set var="WOMAN" value="WOMAN"/>
            <c:set var="ADMIN" value="ROLE_ADMIN"/>
            <c:if test="${sex == MAN}">
                <td>Мужской</td>
            </c:if>
            <c:if test="${sex == WOMAN}">
                <td>Женский</td>
            </c:if>
            <td>
                <c:out value="${user.getRole()}"/></td>
            <td>
                <fmt:formatDate value="${user.getCreated()}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>
                <c:if test="${user.getRole() != ADMIN}">
                <a target="_blank" href="${pageContext.request.contextPath}/admin/showUser/${user.getId()}">Профиль</a>
                </c:if>
                <c:if test="${user.getRole() == ADMIN}">
                    N/A
                </c:if>
            </td>
        </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Сохранить изменения">
</form:form>
</c:if>
<c:if test="${userChangesDto.getUsers().size() < 1}">
    <h3><b>По заданным параметрам никого не найдено!</b></h3>
</c:if>
<br><br><br>
