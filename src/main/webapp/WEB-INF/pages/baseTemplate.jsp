<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basic-styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700&amp;subset=cyrillic"
          rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/messages.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/hamburger.js"></script>
    <script src="${pageContext.request.contextPath}/js/saveCookieFilter.js"></script>
    <script src="${pageContext.request.contextPath}/js/reg.js"></script>
    <script src="${pageContext.request.contextPath}/js/show-hide.js"></script>
    <script src="${pageContext.request.contextPath}/js/changeUserData.js"></script>
    <script src="${pageContext.request.contextPath}/js/changePass.js"></script>
    <script src="${pageContext.request.contextPath}/js/deletePatient.js"></script>
    <title>${PageTitle}</title>
</head>
<body>
<header>
    <c:set var="pageName" value="${PageTitle}"/>
    <c:set var="forbiddenPageName" value="Forbidden"/>
    <c:set var="regPageName" value="Страница регистрации"/>
    <c:if test="${pageName == forbiddenPageName || pageName == regPageName}">
        <c:import url="loginOrAuthHeader.jsp"/>
    </c:if>
    <c:if test="${pageName != forbiddenPageName && pageName != regPageName}">
        <c:import url="userHeader.jsp"/>
    </c:if>
</header>
<main>
    <div class="fixed-container">
        <c:set var="pageBody" value="${PageBody}"/>
        <c:if test="${pageBody != null}">
            <c:import url="${PageBody}"/>
        </c:if>

    </div>
</main>
<footer>
    <div class="fixed-container">
        <section class="left-column">
            <h3>Свяжитесь с нами</h3>

            <ul>
                <li><a href="mailto:university@innopolis.ru">university@innopolis.ru</a></li>
                <hr>
                <li><a href="mailto:a.evtushenko.stc@innopolis.university">a.evtushenko.stc@innopolis.university</a>
                </li>
                <li><a href="mailto:a.galay.stc@innopolis.university">a.galay.stc@innopolis.university</a></li>
                <li><a href="mailto:k.ushakova.stc@innopolis.university">k.ushakova.stc@innopolis.university</a></li>
                <li><a href="mailto:o.petrov.stc@innopolis.university">o.petrov.stc@innopolis.university</a></li>
            </ul>


            <a id="phone-footer" href="tel:+78432039253">+7 (843) 203-92-53</a>

        </section>
        <section class="next-column">
            <h3>Быстрые ссылки</h3>
            <ul>
                <li><a href="/">&nbsp;&nbsp;Главная</a></li>
                <li><a href="#">&nbsp;&nbsp;Связаться</a></li>
            </ul>
        </section>
    </div>
    <div id="copy">&copy; 2020 CarePortal |STC23 project</div>
</footer>

</body>
</html>

