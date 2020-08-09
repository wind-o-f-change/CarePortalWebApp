<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 04.08.2020
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="/anketa-constr" method="post" modelAttribute="anketaDto">
    <span>${message}</span><br>

    <div><label> Название анкеты : <input type="text" name="name" value="${anketaDto.name}"></label></div>

    <c:forEach var="questionDto" items="${questionDtoList}" varStatus="vs">
        <form:checkbox path="anketaDto.questionIdList" value="${questionDto.id}" label="${questionDto.text}" cssClass="styled"/>
        <br>
    </c:forEach>

    <div><input type="submit" value="Сохранить"/></div>
</form>