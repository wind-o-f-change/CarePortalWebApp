<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">
    <h2>Личный кабинет пациента<br>${user.fullName}</h2>
</c:if>
<c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
    <h2>Сведения о пациенте<br>${user.fullName}</h2>
</c:if>
<br>
<c:if test="${not empty message}">
    <div class="ms_error">
        <i class="fa fa-times-circle"></i>
            ${message}
    </div>
</c:if>
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
            <td><fmt:formatDate value="${user.birthDay.getTime()}" pattern="yyyy-MM-dd" var="bday"/>${bday}</td>
        </tr>
        <tr>
            <th>Дата регистрации</th>
            <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <th>Назначенный врач</th>

            <c:set var="doctor" value="${user.doctor.getFullName()}"/>
            <c:if test="${doctor == null}">
                <td>Не назначено</td>
            </c:if>
            <c:if test="${doctor != null}">
                <td>${user.doctor.getFullName()}</td>
            </c:if>
        </tr>
    </table>
    <c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">
        <button id="update-data">Редактировать</button>
    </c:if>
</section>
<br>

<section id="update-form">
    <form action="/patient/saveUsersChanges" method="post">
        <table>
            <input type="hidden" name="id" value="${user.id}">
            <input type="hidden" name="enabled" value="${user.enabled}">
            <input type="hidden" name="roleName" value="${user.role}">

            <tr>
                <th>ФИО</th>
                <td><input type="text" name="fullName" required="required"  value="${user.fullName}"/></td>
            </tr>
            <tr>
                <th>E-mail</th>
                <td><input type="email" name="email" required="required" value="${user.getUsername()}"/></td>
            </tr>
            <tr>
                <th>Пол</th>
                <td>
                    <c:if test="${sex == MAN}">
                        <label> Мужской <input type="radio" value="MAN" name="sex" required="required" checked/> </label><br>
                        <label> Женский <input type="radio" value="WOMAN" name="sex"/> </label>
                    </c:if>
                    <c:if test="${sex == WOMAN}">
                        <label> Мужской <input type="radio" value="MAN" name="sex" required="required" /> </label><br>
                        <label> Женский <input type="radio" value="WOMAN" name="sex" checked/> </label>
                    </c:if>
                </td>

            </tr>
            <tr>
                <th>Дата рождения</th>

                <td><input type="date" name="birthDay" value="${bday}"/></td>
            </tr>
            <tr>
                <th>Дата регистрации</th>
                <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <th>Назначенный врач</th>

                <c:set var="doctor" value="${user.doctor.getFullName()}"/>
                <c:if test="${doctor == null}">
                    <td>Не назначено</td>
                </c:if>
                <c:if test="${doctor != null}">
                    <td>${user.doctor.getFullName()}</td>
                </c:if>
            </tr>
        </table>
        <input type="submit" value="Сохранить"> <input type="reset" value="Сбросить"> <input type="reset" id="reset" value="Отменить">
    </form>
</section>
<br>
<c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">
<input type="image" class="pass-header" alt="Изменение пароля"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="pass-header">Изменение пароля</h3>
<section id="pass-section">
<form class="pass-header" method="post" action="/patient/changePass" >
    <label>Старый пароль<input id="oldpass" name="oldpassword" type="password" /></label><br>
        <label>Новый пароль<input id="pass" name="password" type="password" /></label><br>
        <label>Повторите пароль<input id="passConfirm" type="password" /></label><br>

        <div class="ms_error" id="error-pass">
            <i class="fa fa-times-circle"></i>
               Пароли не совпадают!
        </div>

    <button type="submit" id="savePass">Изменить пароль</button>
</form>
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
</c:if>
<input type="image" class="fill-ankets" alt="Заполненные анкеты"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="fill-ankets">Заполненные анкеты</h3>

<ul id="fill-anket-list">
    <c:forEach var="pa" items="${passedAnkets}">
        <li><a href="${pageContext.request.contextPath}/passed-anketa/${pa.passId}">${pa.anketaName}</a></li>
    </c:forEach>
</ul>
<br><br><br>
