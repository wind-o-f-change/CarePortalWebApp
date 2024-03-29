<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 31.07.2020
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${passedAnketaDtoList.size() < 1}">
    <p>Пока не заполнено ни одной анкеты</p>
    <br>
</c:if>
<c:if test="${passedAnketaDtoList.size() >= 1}">
<table>
    <th>№</th>
    <th>Наименование анкеты</th>
    <th>Дата заполнения</th>
    <th>Ссылка</th>
<c:forEach var="passedAnketaDto" items="${passedAnketaDtoList}" varStatus="vs">
    <tr>
        <td>${vs.index+1}</td>
        <td>${passedAnketaDto.anketaName}</td>
        <td>${passedAnketaDto.created}</td>
        <td><a href="${pageContext.request.contextPath}/passed-anketa/${passedAnketaDto.passId}">Детали</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
