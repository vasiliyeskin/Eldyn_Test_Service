<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Question list</title>
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
    <h2>${param.action == 'create' ? 'Create test' : 'Edit test'}</h2>
    <jsp:useBean id="test" scope="request" class="ru.web.ets.model.Test"/>
    <form method="post" action="tests">
        <input type="hidden" name="testid" value="${test.id}">
        <dl>
            <dt>Text:</dt>
            <dd><input type="text" value="${test.text}" name="testtext"></dd>
        </dl>


        <c:if test="${test.id > 0}">
            <h2>Questions</h2>
            <a href="tests?action=createQuestion&testid=${test.id}">Add Question</a>
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
                <c:forEach items="${test.questionsList}" var="question">
                    <jsp:useBean id="question" scope="page" type="ru.web.ets.model.Question"/>
                    <tr class="normal">
                        <td>${question.text}</td>
                        <td>
                            <table>
                                <c:forEach items="${question.answersList}" var="answer">
                                    <jsp:useBean id="answer" scope="page" type="ru.web.ets.model.TeacherAnswer"/>
                                    <tr class="normal">
                                        <td>${answer.answer.text}</td>
                                        <td><input type="checkbox" value="${answer.right}" name="chbox${answer.id}"
                                                   disabled="disabled" <%=answer.getRight() ? "checked='checked'" : "" %>>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                        <td><a href="tests?action=updateQuestion&testid=${test.id}&qid=${question.id}">Update</a></td>
                        <td><a href="tests?action=deleteQuestion&testid=${test.id}&qid=${question.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>