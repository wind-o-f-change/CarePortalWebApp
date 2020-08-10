<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<input type="image" class="about-me" alt="Личные данные"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="about-me">Личные данные</h3>
<section id="data">
    <c:set value="Страница врача" var="doc"/>
    <c:set value="Страница пациента" var="patient"/>
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
        <c:if test="${PageTitle == patient}">
            <tr>
                <th>Дата рождения</th>
                <td><fmt:formatDate value="${user.birthDay.getTime()}" pattern="yyyy-MM-dd" var="bday"/>${bday}</td>
            </tr>
        </c:if>
        <tr>
            <th>Дата регистрации</th>
            <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <c:if test="${PageTitle == patient}">
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
        </c:if>
    </table>


    <c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
        <c:if test="${PageTitle == doc}">
        <button id="update-data">Редактировать</button>
        </c:if>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">
            <button id="update-data">Редактировать</button>
    </c:if>
</section>
<br>
<section id="update-form">
        <c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">
    <form action="/patient/saveUsersChanges" method="post">
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
        <form action="/doctor/saveUsersChanges" method="post">
        </c:if>
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
<c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">
            <tr>
                <th>Дата рождения</th>
                <td><input type="date" name="birthDay" value="${bday}"/></td>
            </tr>
</c:if>
            <tr>
                <th>Дата регистрации</th>
                <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
            </tr>
<c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">
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
</c:if>
        </table>
        <input type="submit" value="Сохранить"> <input type="reset" value="Сбросить"> <input type="reset" id="reset" value="Отменить">
    </form>
</section>
<br>