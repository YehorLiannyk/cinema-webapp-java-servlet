<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 21.05.2022
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<%--<c:set var="pageContext" value="${pageContext.request.contextPath}"/>--%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <%-- For JS pagiantion--%>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
    <script type="text/javascript">
        <jsp:directive.include file="/WEB-INF/js/jquery.dataTables.min.js"/>
    </script>
    <%-- /For JS pagiantion--%>
    <style>
        <jsp:directive.include file="/WEB-INF/css/bootstrap.min.css" />
        <jsp:directive.include file="/WEB-INF/css/container.css" />
        body {
            background-image: url("images/site_pattern.png");
            background-repeat: repeat;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Cinema</title>

</head>
<body>

