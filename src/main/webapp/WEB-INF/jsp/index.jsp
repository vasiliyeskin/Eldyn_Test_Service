<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <p/>
        <ul>
            <li><a href="students">Students</a></li>
            <li><a href="users">Users</a></li>
            <li><a href="questions">Questions</a></li>
            <li><a href="tests">Tests</a></li>
            <li><a href="statistics">Statistics</a></li>
        </ul>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
