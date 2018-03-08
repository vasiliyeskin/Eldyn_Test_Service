<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="tests" class="navbar-brand"><spring:message code="app.title"/></a>
        <a href="${pageContext.request.contextPath}/" class="navbar-brand" ><spring:message code="app.home"/></a>
        <%--<div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">

                <a class="btn btn-primary" href="">
                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                </a>
            </form>
        </div>--%>
    </div>
</div>

