<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages.app"/>
<hr>
<div class="footer">
    <div class="container">
        <fmt:message key="app.footer"/>
    </div>
</div>

<%--<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="footer">
    <div class="container">
        <spring:message code="app.footer"/>
    </div>
</div>--%>
