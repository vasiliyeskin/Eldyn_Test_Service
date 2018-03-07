<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app" scope="application"/>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="tests" class="navbar-brand"><fmt:message key="app.title"/></a>
        <a href="index.jsp" class="navbar-brand" ><fmt:message key="app.home"/></a>
        <%--<div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">

                <a class="btn btn-primary" href="">
                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                </a>
            </form>
        </div>--%>
    </div>
</div>
