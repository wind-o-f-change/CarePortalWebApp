<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 06.08.2020
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="/new-question" method="post" modelAttribute="questionDto">
    <span>${message}</span><br>

    <h1>Создать новый вопрос</h1>

    <label for="questionText">Текст вопроса:</label>
    <textarea id="questionText" name="text" rows="4" cols="50">${questionDto.text}</textarea>
    <br><br>

    <c:forEach var="answerDto" items="${questionDto.answerDtoList}" varStatus="vs">
        <label for="answerDtoList[${vs.index}]">Вариант ответа ${vs.index+1}</label>
        <form:input id="answerDtoList[${vs.index}]" path="questionDto.answerDtoList[${vs.index}].text"/>
        <br>
    </c:forEach>

    <div><input type="submit" value="Сохранить"/></div>
</form>