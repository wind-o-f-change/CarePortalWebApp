<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 16.07.2020
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${anketa.name}</title>
</head>
<body>
<h1>${anketa.name}</h1>
<form action="/anketa/1">
    <c:forEach var="question" items="${anketa.questionList}">
        <p>${question.text}</p>
        <c:forEach var="answer" items="${question.answerList}">
            <input type="radio" id="${answer.id}" name="${question.id}" value="${answer.id}" required>
            <label for="${answer.id}">${answer.text}</label><br>
        </c:forEach>
        <br>
    </c:forEach>
    <input type="submit" value="Submit">
</form>
</body>
</html>
