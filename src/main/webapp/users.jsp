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
    <h2>Users</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>User ID</th>
            <th>First name</th>
            <th>Last Name</th>
        </tr>
        </thead>
        <c:forEach items="${user}" var="user">
            <jsp:useBean id="user" scope="page" type="ru.web.ets.model.User"/>
            <tr class="normal">
                <td>${user.id}</td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>