<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<p><h5>Вы зашли как: ${admin_name}</h5>
<h3>Кабинет администратора</h3></p>
<div>
    <form method="post" action="/admin">
        <h4>Выбирите действие:</h4>
        <select name="find_action" required style="animation-timing-function: ease">
            <option disabled autofocus>Выбирите действие</option>
            <option value="PATIENT_DOCTOR">Все пользователи</option>
            <option value="PATIENT">Пациенты</option>
            <option value="DOCTOR">Врачи</option>
            <option value="ADMIN">Администраторы</option>
            <option value="NOT_ENABLED">Не подтвержденные</option>
        </select>
        <input type="submit" value="Смотреть"/>
    </form>
</div>
<div>
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
</div>
<br><br><br>