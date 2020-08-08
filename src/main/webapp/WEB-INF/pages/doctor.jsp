<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>Личный кабинет врача<br>${user.fullName}</h2>
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
            <th>Дата регистрации</th>
            <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </table>
    <button id="update-data">Редактировать</button>
</section>
<br>

<section id="update-form">
    <form action="/doctor/saveUsersChanges" method="post">
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
                <th>Дата регистрации</th>
                <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
            </tr>
        </table>
        <input type="submit" value="Сохранить"> <input type="reset" value="Сбросить"> <input type="reset" id="reset" value="Отменить">
    </form>
</section>
<br>

<input type="image" class="pass-header" alt="Изменение пароля"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="pass-header">Изменение пароля</h3>
<section id="pass-section">
    <form class="pass-header" method="post" action="/doctor/changePass" >
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
<input type="image" class="patient-list" alt="Закрепленные пациенты"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="patient-list">Закрепленные пациенты</h3>
<c:if test="${user.getPatients().size() < 1}">
    <p>Закрепленных пациентов в данный момент нет</p>
</c:if>
<c:if test="${user.getPatients().size() >= 1}">
    <ol id="p-list">
        <c:forEach var="patient" items="${user.getPatients()}">
            <li><a target="_blank" href="doctor/showPatient/${patient.getId()}">${patient.fullName}</a></li>
        </c:forEach>
    </ol>
</c:if>
<br><br><br>