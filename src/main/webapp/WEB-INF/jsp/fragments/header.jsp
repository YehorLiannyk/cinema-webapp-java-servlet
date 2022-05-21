<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 21.05.2022
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<%--<c:set var="pageContext" value="${pageContext.request.contextPath}"/>--%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        <jsp:directive.include file="/WEB-INF/css/bootstrap.min.css" />
        <jsp:directive.include file="/WEB-INF/css/container.css" />
    </style>
    <title>Blog Template for Bootstrap</title>
</head>

<body>
<nav class="container-fluid site-header sticky-top">
    <div class="container d-flex flex-column flex-md-row justify-content-between py-2">
        <a class="py-2" href="#">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                 stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                 class="d-block mx-auto">
                <circle cx="12" cy="12" r="10"></circle>
                <line x1="14.31" y1="8" x2="20.05" y2="17.94"></line>
                <line x1="9.69" y1="8" x2="21.17" y2="8"></line>
                <line x1="7.38" y1="12" x2="13.12" y2="2.06"></line>
                <line x1="9.69" y1="16" x2="3.95" y2="6.06"></line>
                <line x1="14.31" y1="16" x2="2.83" y2="16"></line>
                <line x1="16.62" y1="12" x2="10.88" y2="21.94"></line>
            </svg>
        </a>
        <a class="py-2 d-none d-md-inline-block" href=""><fmt:message key="menu.main"/></a>
        <a class="py-2 d-none d-md-inline-block" href="?command=schedule"><fmt:message key="menu.schedule"/></a>
        <a class="py-2 d-none d-md-inline-block" href="#">Features</a>
        <a class="py-2 d-none d-md-inline-block" href="#">Enterprise</a>
        <a class="py-2 d-none d-md-inline-block" href="#">Support</a>
        <a class="py-2 d-none d-md-inline-block" href="#">Pricing</a>
        <a class="py-2 d-none d-md-inline-block" href="#">Cart</a>
    </div>
</nav>
