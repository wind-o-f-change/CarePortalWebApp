<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<p><h5>Вы зашли как: ${admin_name}</h5>
<h3>Кабинет администратора</h3></p>
<div>
    <form method="post" action="/admin">
        <h4>Выбирите действие: <select name="find_action" required>
            <option selected value="NOT_ENABLED">Не подтвержденные</option>
            <option value="ENABLED">Подтвержденные</option>
            <option value="PATIENT_DOCTOR">Все пользователи</option>
            <option value="PATIENT">Пациенты</option>
            <option value="DOCTOR">Врачи</option>
            <option value="ADMIN">Администраторы</option>
        </select> <input type="submit" value="Смотреть"/></h4>


    </form>
</div>
<div class="fixed-container">
    <c:set var="pageBody" value="${list_body}"/>
    <c:if test="${pageBody != null}">
        <c:import url="${list_body}"/>
    </c:if>

</div>
<br><br><br>