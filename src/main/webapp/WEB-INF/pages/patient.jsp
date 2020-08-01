<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>Личный кабинет пациента<br>${user.fullName}</h2>
<br>
<input type="image" class="about-me" alt="Личные данные"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="about-me">Личные данные</h3>
<section id="data">
    <table>
        <tr>
            <th>ФИО</th>
            <td>${user.fullName}</td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td>${user.getUsername()}</td>
        </tr>
        <tr>
            <th>Пол</th>
            <c:set var="sex" value="${user.sex}"/>
            <c:set var="MAN" value="MAN"/>
            <c:set var="WOMAN" value="WOMAN"/>
            <c:if test="${sex == MAN}">
                <td>Мужской</td>
            </c:if>
            <c:if test="${sex == WOMAN}">
                <td>Женский</td>
            </c:if>
        </tr>
        <tr>
            <th>Дата рождения</th>
            <td><fmt:formatDate value="${user.birthDay.getTime()}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <th>Дата регистрации</th>
            <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <th>Назначенный врач</th>

            <c:set var="doctor" value="${user.doctor}"/>
            <c:if test="${doctor == null}">
                <td>Не назначено</td>
            </c:if>
            <c:if test="${doctor != null}">
                <td>${user.doctor}</td>
            </c:if>
        </tr>
    </table>
    <button>Редактировать</button>
</section>
<br>
<input type="image" class="av-ankets" alt="Доступные анкеты"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="av-ankets">Доступные анкеты</h3>
<ul id="anket-list">
    <c:forEach var="anketa" items="${ankets}" varStatus="vsa">
        <li><a href="${pageContext.request.contextPath}/anketa/${anketa.id}">${anketa.name}</a></li>
    </c:forEach>
</ul>
<br>
<input type="image" class="fill-ankets" alt="Заполненные анкеты"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="fill-ankets">Заполненные анкеты</h3>
<ul id="fill-anket-list">
    <a href="#">Ссылка в разработке</a>
</ul>

<br><br><br>
