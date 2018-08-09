<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/practiceDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="container">
    <div class="header clearfix">
        <h3 class="text-muted">Students</h3>
    </div>
    <a class="btn btn-primary" onclick="add();chooseDefault();">
        <span class="fa fa-plus" aria-hidden="true"></span>
        <spring:message code="common.add"/>
    </a>
    <div class="jumbotron">
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th scope="col"><spring:message
                        code="pract.name"/></th>
                <th scope="col"><spring:message
                        code="pract.nameDirection"/></th>
                <th scope="col"><spring:message
                        code="pract.startDate"/></th>
                <th scope="col"><spring:message
                        code="pract.endDate"/></th>
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
                        <label for="name" class="control-label col-xs-3"><spring:message
                                code="pract.name"/></label>

                        <div class="col-xs-9">
                            <textarea type="text" class="form-control" id="name" name="name"
                                      placeholder="<spring:message code="pract.name"/>"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nameDirection" class="control-label col-xs-3"><spring:message
                                code="pract.nameDirection"/></label>

                        <div class="col-xs-9 ">
                            <textarea type="text" class="form-control" id="nameDirection" name="nameDirection"
                                      placeholder="<spring:message code="pract.nameDirection"/>"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="startDate" class="control-label col-xs-3"><spring:message
                                code="pract.startDate"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="startDate" name="startDate"
                                   placeholder="<spring:message code="pract.startDate"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="endDate" class="control-label col-xs-3"><spring:message
                                code="pract.endDate"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="endDate" name="endDate"
                                   placeholder="<spring:message code="pract.endDate"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="save();" class="btn btn-primary">
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
