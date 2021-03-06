<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/organizationDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="container">
    <div class="header clearfix">
        <h3 class="text-muted"><spring:message
                code="docs.orgs"/></h3>
    </div>
    <a class="btn btn-primary" onclick="add()">
        <span class="fa fa-plus" aria-hidden="true"></span>
        <spring:message code="common.add"/>
    </a>
    <div class="jumbotron">
        <table class="table table-striped display" id="datatable">
            <thead class="thead-dark">
            <tr>
                <th scope="col"><spring:message code="org.name"/></th>
                <th scope="col"><spring:message code="org.nameGenitive"/></th>
                <th scope="col"><spring:message code="org.shortname"/></th>
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
                                code="org.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="<spring:message code="org.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nameGenitive" class="control-label col-xs-3"><spring:message
                                code="org.nameGenitive"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="nameGenitive" name="nameGenitive"
                                   placeholder="<spring:message code="org.nameGenitive"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="shortname" class="control-label col-xs-3"><spring:message
                                code="org.shortname"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="shortname" name="shortname"
                                   placeholder="<spring:message code="org.shortname"/>">
                        </div>
                    </div>

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
