<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/studentDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container">
    <div class="header clearfix">
        <h3 class="text-muted">Students</h3>
    </div>
    <a class="btn btn-primary" onclick="add()">
        <span class="fa fa-plus" aria-hidden="true"></span>
        <spring:message code="common.add"/>
    </a>
    <div class="jumbotron">
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th scope="col">Student</th>
                <th scope="col">Adviser</th>
                <th scope="col">Course</th>
                <th scope="col">Training Direction</th>
                <th scope="col">Date of registration</th>
                <th scope="col">Active</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>


<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="firstname" class="control-label col-xs-3"><spring:message
                                code="student.firstname"/> (or Full Name)</label>

                        <div class="col-xs-9 ">
                                <input type="text" class="form-control" id="firstname" name="firstname"
                                       placeholder="<spring:message code="student.firstname"/>">
                            <button type="button" onclick="fillFullName()" class="btn btn-primary">
                                <span aria-hidden="true">Add Full Name</span>
                            </button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="midlename" class="control-label col-xs-3"><spring:message
                                code="student.middlename"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="midlename" name="midlename"
                                   placeholder="<spring:message code="student.middlename"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="lastname" class="control-label col-xs-3"><spring:message
                                code="student.lastname"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="lastname" name="lastname"
                                   placeholder="<spring:message code="student.lastname"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="adviser" class="control-label col-xs-3">Scientific Adviser</label>

                        <div class="col-xs-9">
                            <select type="dropdown" class="form-control" id="adviser" name="adviser"> </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="course" class="control-label col-xs-3"><spring:message
                                code="student.course"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="course" name="course"
                                   placeholder="<spring:message code="student.course"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="trainingDirection" class="control-label col-xs-3">Training Direction</label>

                        <div class="col-xs-9">
                            <select type="dropdown" class="form-control" id="trainingDirection"
                                    name="trainingDirection"> </select>
                        </div>
                    </div>

                    <%--<div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="student.email"/></label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="<spring:message code="student.email"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="phone" class="control-label col-xs-3"><spring:message code="student.phone"/></label>

                        <div class="col-xs-9">
                            <input type="phone" class="form-control" id="phone" name="phone"
                                   placeholder="<spring:message code="student.phone"/>">
                        </div>
                    </div>--%>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="save()" class="btn btn-primary">
                                <span class="fa fa-check" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
<script type="text/javascript">
    var i18n = [];
    i18n["addTitle"] = '<spring:message code="user.add"/>';
    i18n["editTitle"] = '<spring:message code="user.edit"/>';
</script>
</html>
