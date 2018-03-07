<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="../../resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="../../resources/js/userDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container">
    <div class="header clearfix">
        <h3 class="text-muted">Students</h3>
    </div>
    <div class="jumbotron">
        <table class="table table-striped display" id="datatable">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Students ID</th>
                <th scope="col">First name</th>
                <th scope="col">Middle Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Course</th>
                <th scope="col">Email</th>
                <th scope="col">Phone</th>
                <th scope="col">Date of registration</th>
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
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
