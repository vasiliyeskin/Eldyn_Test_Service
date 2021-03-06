<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="app.title"/></title>

    <link rel="stylesheet" href="resources/css/style.css">
    <link href="resources/css/pricing.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="webjars/bootstrap/4.0.0-2/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="webjars/datatables/1.10.16/css/dataTables.bootstrap.min.css">--%>
    <link rel="stylesheet" href="webjars/datatables/1.10.16/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.0/lib/noty.css"/>
    <link rel="stylesheet" href="webjars/datetimepicker/2.5.11/jquery.datetimepicker.css">
    <%--<link rel="shortcut icon" href="resources/images/icon-meal.png">--%>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->

    <%--http://stackoverflow.com/a/24070373/548473--%>
    <script type="text/javascript" src="webjars/jquery/3.2.1/dist/jquery.min.js" defer></script>
    <%--<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>--%>
    <script type="text/javascript" src="webjars/bootstrap/4.0.0-2/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.16/js/jquery.dataTables.min.js" defer></script>
    <%--<script type="text/javascript" src="webjars/datatables/1.10.16/js/dataTables.bootstrap.min.js" defer></script>--%>
    <script type="text/javascript" src="webjars/datatables/1.10.16/js/dataTables.bootstrap4.min.js" defer></script>

    <script type="text/javascript" src="webjars/noty/3.1.0/lib/noty.min.js" defer></script>
    <script type="text/javascript" src="webjars/datetimepicker/2.5.11/build/jquery.datetimepicker.full.min.js" defer></script>

    <script src="https://getbootstrap.com/assets/js/vendor/popper.min.js"></script>
    <script src="https://getbootstrap.com/assets/js/vendor/holder.min.js"></script>


<%--    <script type="text/javascript" class="init">
        $(document).ready(function() {
            $('#datatable').DataTable();
        } );
    </script>--%>


</head>
