<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${userChangesDto.getUsers().size() >= 1}">
    <form:form action="/admin/assignPatients/${id}" method="post" modelAttribute="userChangesDto">
        <table>
            <tr>
                <th>Id</th>
                <th>Ф.И.О</th>
                <th>Email</th>
                <th>Пол</th>
                <th>Дата создания</th>
                <th>Назначить данному врачу
                    <input type="checkbox" id="checkAll"/></th>
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
                        <c:out value="${user.getEmail()}"/></td>
                    <c:set var="sex" value="${user.sex}"/>
                    <c:set var="MAN" value="MAN"/>
                    <c:set var="WOMAN" value="WOMAN"/>
                    <c:if test="${sex == MAN}">
                        <td>Мужской</td>
                    </c:if>
                    <c:if test="${sex == WOMAN}">
                        <td>Женский</td>
                    </c:if>
                       <td> <fmt:formatDate value="${user.getCreated()}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <form:checkbox path="users[${vsq.index}].assignedToDoctor"/>
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
