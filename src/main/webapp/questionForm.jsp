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
    <jsp:useBean id="question" type="ru.web.ets.model.Question" scope="request"/>
    <form method="post" action="questions">
        <input type="hidden" name="id" value="${question.id}">
        <dl>
            <dt>Text:</dt>
            <dd><input type="text" value="${question.text}" name="text"></dd>
        </dl>
        <dl>
            <table>
                <thead>
                <tr>
                    <th>Answer Text</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <c:forEach items="${question.answerList}" var="answer">
                    <jsp:useBean id="answer" scope="page" type="ru.web.ets.model.Answer"/>
                    <tr class="normal">
                        <td>${answer.text}</td>
                        <td><a href="questions?action=update&id=${answer.id}">Update</a></td>
                        <td><a href="questions?action=delete&id=${answer.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
