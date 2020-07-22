<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<h3>Регистрация</h3>
<form action="/registration" method="post">
    <span>${message}</span>
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><label> Full name: <input type="text" name="fullName"/> </label></div>
    <div>
        <label> Я хочу быть пациентом <input type="radio" value="CLIENT" name="roleName"/>  </label><br>
        <label> Я хочу быть доктором <input type="radio" value="DOCTOR" name="roleName"/>  </label><br>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <form method="POST" action="${login}" id="loginForm">
    <div><input type="submit" value="Зарегистрироваться"/></div>
</form>
<br>
<p>Уже зарегистрированы? <a href="/login">Войти в личный кабинет</a></p>
<br>
<a href="/">Вернуться на главную</a><br><br>