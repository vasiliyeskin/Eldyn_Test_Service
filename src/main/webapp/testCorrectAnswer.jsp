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
        <tr>
            <td>
                <table border="1" cellpadding="8" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Text of question</th>
                        <th>Right answers</th>
                    </tr>
                    </thead>
                    <jsp:useBean id="teacherTest" scope="session" class="ru.web.ets.model.Test"/>
                    <c:forEach items="${teacherTest.questionsList}" var="question">
                        <jsp:useBean id="question" scope="page" type="ru.web.ets.model.QuestionForTest"/>
                        <tr class="normal">
                            <td>${question.question.text}</td>
                            <td>
                                <table>
                                    <c:forEach items="${question.question.answersList}" var="answer">
                                        <jsp:useBean id="answer" scope="page" type="ru.web.ets.model.TeacherAnswer"/>
                                        <tr class="normal">
                                            <td>${answer.answer.text}</td>
                                            <td><input type="checkbox" value="${answer.right}"
                                                       disabled="disabled" <%=answer.getRight() ? "checked='checked'" : "" %>
                                                       name="chbox${answer.id}"></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td>
                <table border="1" cellpadding="8" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Text of user's question</th>
                        <th>Your answers</th>
                    </tr>
                    </thead>
                    <jsp:useBean id="userTest" scope="session" class="ru.web.ets.model.Test"/>
                    <c:forEach items="${userTest.questionsList}" var="yquestion">
                        <jsp:useBean id="yquestion" scope="page" type="ru.web.ets.model.QuestionForTest"/>
                        <tr class="normal">
                            <td>${yquestion.question.text}</td>
                            <td>
                                <table>
                                    <c:forEach items="${yquestion.question.userAnswersList}" var="yanswer">
                                        <jsp:useBean id="yanswer" scope="page" type="ru.web.ets.model.UserAnswer"/>
                                        <tr class="normal">
                                            <td><input type="checkbox" value="${yanswer.right}"
                                                       disabled="disabled" <%=yanswer.getRight() ? "checked='checked'" : "" %>
                                                       name="yourchbox${yanswer.id}"></td>
                                            <td>${yanswer.creationdatetime}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>


</section>
</body>
</html>

