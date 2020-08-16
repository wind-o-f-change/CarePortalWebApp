<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Личный кабинет администратора<br>${admin.getFullName()}</h2>
<c:if test="${not empty message}">
    <div class="ms_info">
        <i class="fa fa-check-circle"></i>
            ${message}
    </div>
</c:if>

<h3>Просмотр пользователей</h3>
<div id="show-users-list">

    <form class="selection" method="post" action="/admin" modelAttribute="searchFilter">
        <div id="first-filter">
        <form:label path = "searchFilter.userStatus">Статус</form:label> <br>
            <form:select  multiple = "true" path = "searchFilter.userStatus" >
            <form:option value = "true" label = "Подтверждённый"/>
            <form:option value = "false" label = "Неподтверждённый"/>
        </form:select>
        </div>
        <div id="sec-filter">
        <form:label path = "searchFilter.userRole">Роль</form:label><br>

        <form:select  multiple = "true" path = "searchFilter.userRole">
            <form:options items = "${roleList}" />
        </form:select>
        </div>
        <div id="third-filter">
        <form:label path = "searchFilter.userSex">Пол</form:label><br>

        <form:select multiple = "true" path = "searchFilter.userSex">
            <form:options items = "${sexList}" />
        </form:select>
            </div>
        <br><br>
        <div id="select-input">
        <input  type="submit" value="Выбрать"/><input type="reset" id="selection-reset" value="Сброс"></div>
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