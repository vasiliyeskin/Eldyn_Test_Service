<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/positions.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="container">
    <div class="header clearfix">
        <h3 class="text-muted"><spring:message
                code="position.positions"/></h3>
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
                        code="position.positionIO"/></th>
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
                        <label for="positionIO" class="control-label col-xs-3"><spring:message
                                code="position.positionIO"/></label>

                        <div class="col-xs-9">
                            <textarea type="text" class="form-control" id="positionIO" name="positionIO"
                                      placeholder="<spring:message code="position.positionIO"/>"></textarea>
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
