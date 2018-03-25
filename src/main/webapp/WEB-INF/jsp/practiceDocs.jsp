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
        <h1 class="display-3">Get documents for practice</h1>
        <p>Form documents.</p>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <h2>Practice</h2>
            <p><select type="dropdown" class="form-control" id="practice" name="practice" onchange="selectPractice();"> </select></p>
            <h2>Start Date</h2>
            <p><input type="text" class="form-control" id="start" name="start"/></p>
            <h2>End Date</h2>
            <p><input type="dropdown" class="form-control" id="end" name="end"/></p>
        </div>

        <div class="col-md-3">
            <h2>Training Direction</h2>
            <p><select type="dropdown" class="form-control" id="trainingDirection"
                       name="trainingDirection"> </select></p>

        </div>

        <div class="col-md-3">
            <h2>Cource</h2>
            <p><select type="dropdown" class="form-control" id="course"
                       name="course"> </select></p>
        </div>

        <div class="col-md-3">
            <h2>Curator</h2>
            <p><select type="dropdown" class="form-control" id="curator"
                       name="curator"> </select></p>
        </div>
    </div>
    <p><a class="btn btn-primary btn-lg" role="button" onclick="getDocs();">Get docs</a></p>
    <pre id="result"></pre>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
