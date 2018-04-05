<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#"><spring:message code="app.title"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/" class="navbar-brand"><spring:message
                        code="app.home"/><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="dropdown01" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="true"><spring:message
                        code="common.docs"/><b class="caret"></b></a>
                <ul class="dropdown-menu" aria-labelledby="dropdown01">
                    <li><a class="dropdown-item" href="practiceDocs"><spring:message
                            code="docs.practice"/></a></li>
                    <li><a class="dropdown-item" href="students"><spring:message
                            code="docs.students"/></a></li>
                    <li><a class="dropdown-item" href="scientificadviser"><spring:message
                            code="docs.advisers"/></a></li>
                    <li><a class="dropdown-item" href="organizations"><spring:message
                            code="docs.orgs"/></a></li>
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="dropdown02" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="true">Admin<b class="caret"></b></a>
                <ul class="dropdown-menu" aria-labelledby="dropdown01">
                    <li><a class="dropdown-item" href="users">Users</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle"
                   data-toggle="dropdown">${pageContext.response.locale}<b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item"
                           href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a>
                    </li>
                    <li><a class="dropdown-item"
                           href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a>
                    </li>
                </ul>
            </li>
        </ul>

        <a class="btn btn-primary" href="logout">
            <i class="fa fa-sign-out" aria-hidden="true"></i>
        </a>
    </div>
</nav>
<br/>
<br/>
<br/>
<br/>