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
    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal"><spring:message
                        code="common.docs"/></h4>
            </div>
            <div class="card-body text-left">
                <ul aria-labelledby="dropdown01">
                    <li><a href="practiceDocs"><spring:message
                            code="docs.practice"/></a></li>
                    <li><a href="students"><spring:message
                            code="docs.students"/></a></li>
                    <li><a href="curators"><spring:message
                            code="docs.curators"/></a></li>
                    <li><a href="scientificadviser"><spring:message
                            code="docs.advisers"/></a></li>
                    <li><a href="organizations"><spring:message
                            code="docs.orgs"/></a></li>
                </ul>
            </div>
        </div>
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Tests</h4>
            </div>
            <div class="card-body text-left">
                <ul>
                    <li><a href="students">Students</a></li>
                    <li><a href="users">Users</a></li>
                    <li><a href="questions">Questions</a></li>
                    <li><a href="tests">Tests</a></li>
                    <li><a href="statistics">Statistics</a></li>
                </ul>
            </div>
        </div>
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Admin</h4>
            </div>
            <div class="card-body text-left">
                <ul>
                    <li><a href="users">Users</a></li>
                </ul>
            </div>
        </div><div class="card mb-4 box-shadow">
        <div class="card-header">
            <h4 class="my-0 font-weight-normal"></h4>
        </div>
        <div class="card-body">

        </div>
    </div>
    </div>
</div>


    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
