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
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        <spring:message code="common.add"/>
    </a>
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
                <th scope="col">Active</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${students}" var="student">
                <jsp:useBean id="student" scope="page" type="ru.web.ets.model.forDocs.Student"/>
                <tr class="${student.active ? '' : 'disabled'}">
                    <td>${student.id}</td>
                    <td>${student.firstname}</td>
                    <td>${student.midlename}</td>
                    <td>${student.lastname}</td>
                    <td>${student.course}</td>
                    <td>${student.email}</td>
                    <td>${student.phone}</td>
                    <td><fmt:formatDate value="${student.registered}" pattern="dd-MMMM-yyyy"/></td>
                    <td><input type="checkbox"
                               <c:if test="${student.active}">checked</c:if> onclick="enable($(this), ${student.id})"/>
                    </td>
                    <td><a><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
                    <td><a onclick="deleteRow(${student.id})"><span class="glyphicon glyphicon-remove"
                                                                    aria-hidden="true"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="student.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="firstname" class="control-label col-xs-3"><spring:message
                                code="student.firstname"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="firstname" name="firstname"
                                   placeholder="<spring:message code="student.firstname"/>">
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
                        <label for="course" class="control-label col-xs-3"><spring:message
                                code="student.course"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="course" name="course"
                                   placeholder="<spring:message code="student.course"/>">
                        </div>
                    </div>

                    <div class="form-group">
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
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="save()" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
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
</html>
