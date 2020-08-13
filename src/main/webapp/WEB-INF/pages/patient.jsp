<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">
    <h2>Личный кабинет пациента<br>${user.fullName}</h2>
</c:if>
<br>
<section class="message-section">
    <c:if test="${not empty error}">
        <div class="ms_error">
            <i class="fa fa-times-circle"></i>
                ${error}
        </div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="ms_info">
            <i class="fa fa-check-circle"></i>
                ${message}
        </div>
    </c:if>
</section>
<c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
    <h2>Сведения о пациенте<br>${user.fullName}</h2>
</c:if>

<c:import url="personalData.jsp"/>


<c:if test="${pageContext.request.isUserInRole('ROLE_PATIENT')}">

<c:import url="passChange.jsp"/>

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

<div id="fill-anket-list">
    <c:import url="${passedAnketaTable}"/>
</div>
<br><br><br>
