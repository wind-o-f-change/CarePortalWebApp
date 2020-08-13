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
<br>
<c:if test="${not empty message}">
    <div class="ms_info">
        <i class="fa fa-check-circle"></i>
            ${message}
    </div>
</c:if>
<br>
<form action="/new-question" method="post" modelAttribute="questionDto">
    <div class="reg">
    <h1>Создать новый вопрос</h1>

    <label>Текст вопроса:
        <textarea id="questionText" name="text" rows="4" cols="50">${questionDto.text}</textarea></label>

    <br><br>

    <c:forEach var="answerDto" items="${questionDto.answerDtoList}" varStatus="vs">
        <label >Вариант ответа ${vs.index+1}
            <form:input size="35" id="answerDtoList[${vs.index}]" path="questionDto.answerDtoList[${vs.index}].text"/>
        </label>

        <br>
    </c:forEach>
    </div>
    <div><input type="submit" value="Сохранить"/></div>
</form>