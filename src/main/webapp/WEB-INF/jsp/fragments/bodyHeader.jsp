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
                   aria-haspopup="true" aria-expanded="true">Docs<b class="caret"></b></a>
                <ul class="dropdown-menu" aria-labelledby="dropdown01">
                    <li><a class="dropdown-item" href="practiceDocs">Documents for Practice</a></li>
                    <li><a class="dropdown-item" href="students">Students</a></li>
                    <li><a class="dropdown-item" href="curators">Curators</a></li>
                    <li><a class="dropdown-item" href="scientificadviser">Scientific Adviser</a></li>
                    <li><a class="dropdown-item" href="organizations">Organizations</a></li>
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
    </div>
</nav>
<br/>
<br/>
<br/>
<br/>