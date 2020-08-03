<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 02.08.2020
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
    <c:forEach var="questionDto" items="${passedAnketaDto.questionDtoList}" varStatus="vs">
        <tr>
            <td>${vs.index+1}</td>
            <td>${questionDto.text}</td>
            <td>${questionDto.chosenAnswerText}</td>
        </tr>
    </c:forEach>
</table>