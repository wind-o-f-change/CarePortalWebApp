<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="fixed-container">
    <div id="logo"><img alt="logo" src="${pageContext.request.contextPath}/img/logo.png"></div>
    <div id="mail-us">
        <div class="contacts">Напишите нам: <br></div>
        <a href="mailto:university@innopolis.ru">university@innopolis.ru</a>
    </div>
    <div id="call-us">
        <div class="contacts">Позвоните нам: <br></div>
        <a href="tel:+78432039253">+7 (843) 203-92-53</a>
    </div>
    <hr>
</div>
<div class="fixed-container">
    <div id="toggle" class="toggle" onclick="getActive()">
        <span id="ham"></span>
    </div>
    <nav>
        <ul id="menu" class="menu">
            <li><a href="${pageContext.request.contextPath}" class="checked-a">Личный кабинет</a></li>
            <li><a href="/logout">Выйти</a></li>
        </ul>
    </nav>
</div>