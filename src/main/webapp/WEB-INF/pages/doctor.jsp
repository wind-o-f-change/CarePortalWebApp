<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>Личный кабинет врача<br>${user.fullName}</h2>
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
<c:import url="personalData.jsp"/>

<c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
<c:import url="passChange.jsp"/>
</c:if>
<input type="image" class="patient-list" alt="Закрепленные пациенты"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="patient-list">Закрепленные пациенты</h3>
<c:if test="${user.getPatients().size() < 1}">
    <p>Закрепленных пациентов в данный момент нет</p>
    <br>
</c:if>
<c:if test="${user.getPatients().size() >= 1}">
    <ol id="p-list">
        <c:forEach var="patient" items="${user.getPatients()}">
            <li><a target="_blank" href="${pageContext.request.contextPath}/doctor/showPatient/${patient.getId()}">${patient.fullName}</a></li>
        </c:forEach>
    </ol>
    <br>
</c:if>
<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
    <input type="image" class="new-patients" alt="Новые пациенты"
           src="${pageContext.request.contextPath}/img/point.png"/>
    <h3 class="new-patients">Назначение новых пациентов</h3>
    <div id="free-patients">
        <c:set var="pageBody" value="${list_body}"/>
        <c:if test="${pageBody != null}">
            <c:import url="${list_body}"/>
        </c:if>
    </div>
</c:if>
<br><br><br>