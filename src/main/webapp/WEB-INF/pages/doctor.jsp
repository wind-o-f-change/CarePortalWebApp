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

<c:import url="personalData.jsp"/>

<c:import url="passChange.jsp"/>

<input type="image" class="patient-list" alt="Закрепленные пациенты"
       src="${pageContext.request.contextPath}/img/point.png"/>
<h3 class="patient-list">Закрепленные пациенты</h3>
<c:if test="${user.getPatients().size() < 1}">
    <p>Закрепленных пациентов в данный момент нет</p>
</c:if>
<c:if test="${user.getPatients().size() >= 1}">
    <ol id="p-list">
        <c:forEach var="patient" items="${user.getPatients()}">
            <li><a target="_blank" href="${pageContext.request.contextPath}/doctor/showPatient/${patient.getId()}">${patient.fullName}</a></li>
        </c:forEach>
    </ol>
</c:if>
<br><br><br>