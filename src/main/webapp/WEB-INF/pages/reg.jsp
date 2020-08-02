<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<h3>Регистрация</h3>
<form action="/registration" method="post">
    <span>${message}</span>
    <div><label> Ваш email : <input type="email" name="email"/> </label></div>
    <div><label> Пароль : <input type="password" name="password"/> </label></div>
    <div><label> Ф.И.О : <input type="text" name="fullName"/> </label></div>
    <div>
        <label> Я хочу быть пациентом <input id ="patient" type="radio" value="ROLE_PATIENT" name="roleName"/>  </label><br>
        <label> Я хочу быть доктором <input type="radio" value="ROLE_DOCTOR" name="roleName"/>  </label><br>
        <label> Я хочу быть admin <input type="radio" value="ROLE_ADMIN" name="roleName"/>  </label><br>
    </div>
    <div>
        <label> Я мужчина <input type="radio" value="MAN" name="sex"/>  </label><br>
        <label> Я женщина <input type="radio" value="WOMAN" name="sex"/>  </label><br>
    </div>
    <div id="date">
        <label> Дата рождения <input type="date" name="birthDay"/>  </label><br>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <div><input type="submit" value="Зарегистрироваться"/></div>
</form>
<br>
<p>Уже зарегистрированы? <a href="/login">Войти в личный кабинет</a></p>
<br>
<br>