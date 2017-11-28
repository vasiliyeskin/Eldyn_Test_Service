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

        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Text of question</th>
                <th>Right answers</th>
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
                                    <td><input type="checkbox" value="${answer.correct}" disabled="disabled" <%=answer.isCorrect() ? "checked='checked'" : "" %> name="chbox${answer.id}"></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </c:forEach>
        </table>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Text of question</th>
            <th>Your answers</th>
        </tr>
        </thead>
        <c:forEach items="${yourAnswers}" var="yourquestion">
            <jsp:useBean id="yourquestion" scope="page" type="ru.web.ets.model.Question"/>
            <tr class="normal">
                <td>${yourquestion.text}</td>
                <td>
                    <table>
                        <c:forEach items="${yourquestion.answerList}" var="yanswer">
                            <jsp:useBean id="yanswer" scope="page" type="ru.web.ets.model.Answer"/>
                            <tr class="normal">
                                <td>${yanswer.text}</td>
                                <td><input type="checkbox" value="${yanswer.correct}" disabled="disabled" <%=yanswer.isCorrect() ? "checked='checked'" : "" %> name="yourchbox${yanswer.id}"></td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>

