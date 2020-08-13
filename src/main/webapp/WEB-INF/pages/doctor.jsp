<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<div id="p-list">
    <c:if test="${user.getPatients().size() < 1}">
        <p>Закрепленных пациентов в данный момент нет</p>
        <br>
    </c:if>
<c:if test="${user.getPatients().size() >= 1}">
    <c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
    <table>
        <tr>
            <th>Id</th>
            <th>Ф.И.О</th>
            <th>Email</th>
            <th>Пол</th>
            <th>Дата создания</th>
            <th>Ссылка на профиль</th>
        </tr>
        <c:forEach var="patient" items="${user.getPatients()}">
            <tr>
                <td>
                    <c:out value="${patient.getId()}"/></td>
                </td>
                <td>
                    <c:out value="${patient.getFullName()}"/></td>
                <td>
                    <c:out value="${patient.getEmail()}"/></td>
                <c:set var="sex" value="${patient.sex}"/>
                <c:set var="MAN" value="MAN"/>
                <c:set var="WOMAN" value="WOMAN"/>
                <c:if test="${sex == MAN}">
                    <td>Мужской</td>
                </c:if>
                <c:if test="${sex == WOMAN}">
                    <td>Женский</td>
                </c:if>
                <td> <fmt:formatDate value="${patient.getCreated()}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>
                    <a target="_blank" href="${pageContext.request.contextPath}/doctor/showPatient/${patient.getId()}">Профиль</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>

</c:if>

<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <form:form action="/admin/deleteFromDoc/${id}" method="post" modelAttribute="patientsDto">
        <table>
            <tr>
                <th>Id</th>
                <th>Ф.И.О</th>
                <th>Email</th>
                <th>Пол</th>
                <th>Дата создания</th>
                <th>Ссылка на профиль</th>
                <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <th>Открепить</th>
                </c:if>
            </tr>
            <c:forEach var="patient" items="${patientsDto.getUsers()}" varStatus="vsq">
                <tr>
                    <td>
                        <form:hidden path="users[${vsq.index}].id" />
                        <c:out value="${patient.getId()}"/></td>
                    </td>
                    <td>
                        <c:out value="${patient.getFullName()}"/></td>
                    <td>
                        <c:out value="${patient.getEmail()}"/></td>
                    <c:set var="sex" value="${patient.sex}"/>
                    <c:set var="MAN" value="MAN"/>
                    <c:set var="WOMAN" value="WOMAN"/>
                    <c:if test="${sex == MAN}">
                        <td>Мужской</td>
                    </c:if>
                    <c:if test="${sex == WOMAN}">
                        <td>Женский</td>
                    </c:if>
                    <td> <fmt:formatDate value="${patient.getCreated()}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <a target="_blank" href="${pageContext.request.contextPath}/doctor/showPatient/${patient.getId()}">Профиль</a>
                    </td>
                    <td class="hidden-checkbox">
                        <i class="fa fa-times-circle">
                        <form:checkbox checked="checked" path="users[${vsq.index}].assignedToDoctor"/>
                        </i>
                    </td>
                </tr>

            </c:forEach>

        </table>
            <input type="submit" value="Сохранить изменения"> <input type="reset" id="pat-reset" value="Сбросить">
        </form:form>
        <br>


</c:if>
</c:if>
</div>
<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
<br>
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