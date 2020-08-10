<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<input type="image" class="pass-header" alt="Изменение пароля"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="pass-header">Изменение пароля</h3>
<section id="pass-section">
<c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
    <form  class="pass-header" method="post" action="/doctor/changePass" >
</c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">
    <form class="pass-header" method="post" action="/patient/changePass" >
        </c:if>
        <div class="reg">
        <label>Старый пароль<input id="oldpass" name="oldpassword" type="password" /></label><br>
        <label>Новый пароль<input id="pass" name="password" type="password" /></label><br>
        <label>Повторите пароль<input id="passConfirm" type="password" /></label><br>
        </div>
        <div class="ms_error" id="error-pass">
            <i class="fa fa-times-circle"></i>
            Пароли не совпадают!
        </div>

        <button type="submit" id="savePass">Изменить пароль</button>
    </form>
</section>
<br>