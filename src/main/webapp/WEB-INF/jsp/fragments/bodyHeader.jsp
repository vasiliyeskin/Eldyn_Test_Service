<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="tests" class="navbar-brand"><spring:message code="app.title"/></a>
        <a href="${pageContext.request.contextPath}/" class="navbar-brand"><spring:message code="app.home"/></a>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a></li>
                        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <%--<div class="collapse navbar-collapse">
        <form class="navbar-form navbar-right">

            <a class="btn btn-primary" href="">
                <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
            </a>
        </form>
    </div>--%>
</div>
</div>

