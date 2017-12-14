<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Question</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create question' : 'Edit question'}</h2>
    <hr>
    <jsp:useBean id="test" scope="request" class="ru.web.ets.model.Test"/>
    <jsp:useBean id="question" scope="request" type="ru.web.ets.model.Question"/>
    <form method="post" action="questions">
        <input type="hidden" name="testid" value="${test.id}">
        <input type="hidden" name="id" value="${question.id}">
        <dl>
            <dt>Text:</dt>
            <dd><input type="text" value="${question.text}" name="text"></dd>
        </dl>
        <dl>
            <c:if test="${question.text.length() > 0}">
            <table  border="1" cellpadding="8" cellspacing="0">
                <thead>
                <tr>
                    <th>Answer Text</th>
                    <th></th>
                </tr>
                </thead>
                <c:forEach items="${question.answersList}" var="answer">
                    <jsp:useBean id="answer" scope="page" type="ru.web.ets.model.TeacherAnswer"/>
                    <tr class="normal">
                        <td><input type="hidden" name="id${answer.id}" value="${answer.id}">
                            <dl>
                                <dt>Text:</dt>
                                <dd><input type="text" value="${answer.answer.text}" name="text${answer.id}"></dd>
                                <dt>is correct answer:</dt>
                                <dd><input type="checkbox" value="${answer.right}" name="chbox${answer.id}"  <%=answer.getRight() ? "checked='checked'" : "" %>></dd>
                            </dl>
                        </td>
                        <td><a href="questions?action=deleteAns&id=${question.id}&idAns=${answer.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
                <dl>
                    <dt>Add answer:</dt>
                    <dd><input type="text" name="addAnswerText"><button type="submit">Add</button></dd>
                </dl>
            </c:if>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
