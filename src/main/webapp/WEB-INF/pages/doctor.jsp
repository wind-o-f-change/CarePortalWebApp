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
<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
    <h3>Назначение новых пациентов</h3>
    <form method="post" action="${pageContext.request.contextPath}/admin/showUser/${user.getId()}" class="hidden-select">
        <h4>Показать всех пациентов </h4>
        </select> <input type="submit" value="Смотреть"/></h4>
    </form>
    <div>
        <c:set var="pageBody" value="${list_body}"/>
        <c:if test="${pageBody != null}">
            <c:import url="${list_body}"/>
        </c:if>

    </div>
</c:if>
<br><br><br>