<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Students</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Students ID</th>
            <th>First name</th>
            <th>Middle Name</th>
            <th>Last Name</th>
            <th>Course</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Date of registration</th>
        </tr>
        </thead>
        <c:forEach items="${students}" var="student">
            <jsp:useBean id="student" scope="page" type="ru.web.ets.model.forDocs.Student"/>
            <tr class="normal">
                <td>${student.id}</td>
                <td>${student.firstname}</td>
                <td>${student.midlename}</td>
                <td>${student.lastname}</td>
                <td>${student.course}</td>
                <td>${student.email}</td>
                <td>${student.phone}</td>
                <td>${student.registered}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
