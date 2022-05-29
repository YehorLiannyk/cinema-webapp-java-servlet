<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 22.05.2022
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<fmt:bundle basename="i18n">
    <fmt:message key="error.pageTitle" var="pageTitle"/>
    <fmt:message key="error.oops" var="oops"/>
    <fmt:message key="error.smthGoesWrong" var="smthGoesWrong"/>
    <fmt:message key="error.backPrevPage" var="backPrevPage"/>
    <fmt:message key="error.goMainPage" var="goMainPage"/>
</fmt:bundle>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<div class="d-flex justify-content-center align-items-center" id="main" style="background-color: wheat;">
    <h1 class="mr-3 pr-3 align-top border-right inline-block align-content-center">${oops}</h1>
    <div class="inline-block align-middle">
        <h2 class="font-weight-normal lead">${smthGoesWrong}</h2>
        <h2 class="font-weight-normal lead" id="desc">${errorMessage}</h2>
        <a href="${pageContext.request.getHeader("referer")}" class="btn btn-link">${backPrevPage}</a>
        <a href="main" class="btn btn-link">${goMainPage}</a>
    </div>
</div>
