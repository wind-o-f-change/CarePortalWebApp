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
    <form method="post" action="/admin" modelAttribute="search">
        <table>
            <tr>
                <td><form:label path = "search.userStatus">Статус</form:label></td>
                <td>
                    <form:select multiple = "true" path = "search.userStatus">
                        <form:option value = "true" label = "Подтверждённый"/>
                        <form:option value = "false" label = "Неподтверждённый"/>
                    </form:select>
                </td>
            </tr>

            <tr>
                <td><form:label path = "search.userRole">Роль</form:label></td>
                <td>
                    <form:select multiple = "true" path = "search.userRole">
                        <form:options items = "${roleList}" />
                    </form:select>
                </td>
            </tr>

            <tr>
                <td><form:label path = "search.userSex">Пол</form:label></td>
                <td>
                    <form:select multiple = "true" path = "search.userSex">
                        <form:options items = "${sexList}" />
                    </form:select>
                </td>
            </tr>

            <input type="submit" value="Смотреть"/>
        </table>
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