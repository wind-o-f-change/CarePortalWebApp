<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 16.07.2020
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>${passDto.anketaName}</h1>
<form method="POST" action="/passed-anketa" modelAttribute="passDto">
    <form:hidden path="passDto.anketaId"/>
    <form:hidden path="passDto.anketaName"/>
    <c:forEach var="question" items="${passDto.questionDtoList}" varStatus="vsq">
        <form:hidden path="passDto.questionDtoList[${vsq.index}].id"/>
        <form:hidden path="passDto.questionDtoList[${vsq.index}].text"/>
        <p>${question.text}</p>
        <c:forEach var="answer" items="${question.answerDtoList}" varStatus="vsa">
            <form:radiobutton path="passDto.questionDtoList[${vsq.index}].chosenAnswerId" value="${answer.id}" label="${answer.text}" cssClass="styled"/>
        </c:forEach>
        <br>
    </c:forEach>
    <input type="submit" value="Сохранить">
</form>

