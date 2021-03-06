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
    <h2>Tests</h2>

    <a href="tests?action=create">Add Tests</a>
    <%--<form method="post" action="testAndQuestions">--%>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Test ID</th>
            <th>Text</th>
            <th>Creator's Email</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${tests}" var="test">
            <jsp:useBean id="test" scope="page" type="ru.web.ets.model.Test"/>
            <tr class="normal">
                <td>${test.id}</td>
                <td><a href="test?testid=${test.id}">${test.text}</a> </td>
                <td>${test.creator.email}</td>
                <td><a href="tests?action=update&testid=${test.id}">Update</a></td>
                <td><a href="tests?action=delete&testid=${test.id}">Delete</a></td>
                    <%--                <td>
                                        <table>
                                            <c:forEach items="${question.answerList}" var="answer">
                                                <jsp:useBean id="answer" scope="page" type="ru.web.ets.model.Answer"/>
                                                <tr class="normal">
                                                    <td>${answer.text}</td>
                                                    <td><input type="checkbox" value="${answer.correct}" name="chbox${answer.id}"></td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>--%>
            </tr>
        </c:forEach>
    </table>
    <%--<button type="submit">Submit</button>--%>
    <%--</form>--%>
</section>
</body>
</html>