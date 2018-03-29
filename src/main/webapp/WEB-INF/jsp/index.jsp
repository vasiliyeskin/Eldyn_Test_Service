<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h1>Home page</h1>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <h2 style="background: #FAFAFA" >Docs</h2>
            <ul aria-labelledby="dropdown01">
                <li><a href="practiceDocs">Documents for Practice</a></li>
                <li><a href="students">Students</a></li>
                <li><a href="curators">Curators</a></li>
                <li><a href="scientificadviser">Scientific Adviser</a></li>
                <li><a href="organizations">Organizations</a></li>
            </ul>
        </div>
        <div class="col-md-3">
            <h2 style="background: #FAFAFA">Tests</h2>
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
</div>



<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
