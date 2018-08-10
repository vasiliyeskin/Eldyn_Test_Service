<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/practiceDocs.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">
    <div class="container">
        <h1 class="display-3"><spring:message code="docs.getpractice"/></h1>
        <p>Form documents.</p>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <h4 class="mb-3"><spring:message code="docs.pract"/></h4>
            <p><select type="dropdown" class="form-control" id="practice" name="practice" onchange="selectPractice();"> </select></p>
            <h4 class="mb-3"><spring:message code="docs.startdate"/></h4>
            <p><input type="text" class="form-control" id="start" name="start"/></p>
        </div>

        <div class="col-md-3">
            <h4 class="mb-3"><spring:message code="docs.trainingdir"/></h4>
            <p><select type="dropdown" class="form-control" id="trainingDirection"
                       name="trainingDirection" onchange="updateStudentsList();"> </select></p>
            <h4 class="mb-3"><spring:message code="docs.endtdate"/></h4>
            <p><input type="dropdown" class="form-control" id="end" name="end"/></p>

        </div>

        <div class="col-md-3">
            <h4 class="mb-3"><spring:message code="docs.course"/></h4>
            <p><select type="dropdown" class="form-control" id="course"
                       name="course" onchange="updateStudentsList();"> </select></p>
        </div>

        <div class="col-md-3">
            <h4 class="mb-3"><spring:message code="docs.curator"/></h4>
            <p><select type="dropdown" class="form-control" id="curator"
                       name="curator" onchange="updateStudentsList();"> </select></p>
        </div>
    </div>
    <p><a class="btn btn-primary btn-lg" role="button" onclick="getDocs();">Get docs</a></p>
    <pre id="result"></pre>
    <iframe id="downloadFrame" style="display:none"></iframe>
    <div class="jumbotron">
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th scope="col"><spring:message
                        code="docs.student"/></th>
                 <th scope="col"><spring:message
                        code="docs.course"/></th>
                <th scope="col"><spring:message
                        code="docs.trainingdir"/></th>
                <th scope="col"><spring:message
                        code="docs.registration"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
