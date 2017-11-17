<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Questions</h2>
    <a href="questions?action=create">Add Questions</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Text</th>
            <th>Answer</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${questions}" var="question">
            <jsp:useBean id="question" scope="page" type="ru.web.ets.model.Question"/>
            <tr class="normal">
                <td>${question.text}</td>
                <td>
                    <table>
                        <c:forEach items="${question.answerList}" var="answer">
                            <jsp:useBean id="answer" scope="page" type="ru.web.ets.model.Answer"/>
                            <tr class="normal">
                                <td>${answer.text}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td><a href="questions?action=update&id=${question.id}">Update</a></td>
                <td><a href="questions?action=delete&id=${question.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>