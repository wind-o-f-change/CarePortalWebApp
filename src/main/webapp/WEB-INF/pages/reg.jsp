<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<h3>Регистрация</h3>
<form action="/registration" method="post">
    <c:if test="${not empty error}">
        <div class="ms_error">
            <i class="fa fa-times-circle"></i>
                ${error}
        </div>
    </c:if>
    <div class="reg">
    <div><label> Ваш email : <input type="email" name="email" required="required"/> </label></div>
    <div><label> Пароль : <input type="password" name="password" required="required"/> </label></div>
    <div><label> Ф.И.О : <input type="text" name="fullName" required="required"/> </label></div>
    </div>
    <hr>
    <div class="reg radio-reg">
    <div>
        <label> Я хочу быть пациентом <input id ="patient" type="radio" value="ROLE_PATIENT" name="roleName" required="required"/>  </label><br>
        <label> Я хочу быть доктором <input type="radio" value="ROLE_DOCTOR" name="roleName"/>  </label><br>
        <label> Я хочу быть admin <input type="radio" value="ROLE_ADMIN" name="roleName"/>  </label><br>
    </div>
    </div>
    <hr>
    <div class="reg radio-reg">
    <div>
        <label> Я мужчина <input type="radio" value="MAN" name="sex" required="required"/>  </label><br>
        <label> Я женщина <input type="radio" value="WOMAN" name="sex"/>  </label><br>
    </div>
    </div>
    <hr>
    <div class="reg date-reg">
    <div id="date">
        <label> Дата рождения <input type="date" name="birthDay" />  </label><br>
    </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <div><input type="submit" value="Зарегистрироваться"/></div>
</form>
<br>
<p>Уже зарегистрированы? <a href="/login">Войти в личный кабинет</a></p>
<br>
<br>