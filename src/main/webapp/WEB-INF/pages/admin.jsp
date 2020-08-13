<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<h2>Личный кабинет администратора<br>${admin.getFullName()}</h2>
<c:if test="${not empty message}">
    <div class="ms_info">
        <i class="fa fa-check-circle"></i>
            ${message}
    </div>
</c:if>

<h3>Просмотр пользователей</h3>
<div id="show-users-list">
    <form method="post" action="/admin">
        <h4>Выберите действие: <select name="find_action" required>
            <option selected value="NOT_ENABLED">Не подтвержденные</option>
            <option value="ENABLED">Подтвержденные</option>
            <option value="PATIENT_DOCTOR">Все пользователи</option>
            <option value="PATIENT">Пациенты</option>
            <option value="DOCTOR">Врачи</option>
            <option value="ADMIN">Администраторы</option>
        </select> <input type="submit" value="Смотреть"/></h4>


    </form>
</div>
<br>
<div>
    <c:set var="pageBody" value="${list_body}"/>
    <c:if test="${pageBody != null}">
        <c:import url="${list_body}"/>
    </c:if>
</div>
<br>
<br>
<hr>
<a href="/anketa-constr"><button>Конструктор анкет</button></a>&nbsp;&nbsp;
<a href="/new-question"><button>Конструктор вопросов</button></a>
<br><br><br>