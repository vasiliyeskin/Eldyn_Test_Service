<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Tests</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Test</h2>

    <form method="post" action="test">
            <jsp:useBean id="currenttest" scope="session" class="ru.web.ets.model.Test" type="ru.web.ets.model.Test"/>
        <input type="text" hidden="hidden" value="${currenttest.id}" name="testid" id="testid">

            <table border="1" cellpadding="8" cellspacing="0">
                <thead>
                <tr>
                    <th>Text</th>
                    <th>Variants of answers</th>
                </tr>
                </thead>
                <c:forEach items="${currenttest.questionsList}" var="question">
                    <jsp:useBean id="question" scope="page" type="ru.web.ets.model.QuestionForTest"/>
                    <tr class="normal">
                        <td>${question.question.text}</td>
                        <td>
                            <table>
                                <c:forEach items="${question.question.answersList}" var="tanswer">
                                    <jsp:useBean id="tanswer" scope="page" type="ru.web.ets.model.TeacherAnswer"/>
                                    <tr class="normal">
                                        <td>${tanswer.answer.text}</td>
                                        <td><input type="checkbox" value="${tanswer.right}" name="chbox${tanswer.id}">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        <button type="submit">Submit</button>
    </form>
</section>
</body>
</html>
