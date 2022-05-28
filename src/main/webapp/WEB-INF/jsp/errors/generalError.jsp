<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 24.05.2022
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtg" %>
<fmt:bundle basename="i18n">
    <fmt:message key="error.pageTitle" var="pageTitle"/>
    <fmt:message key="error.cantFindPage" var="cantFindPage"/>
    <fmt:message key="error.failedReqFrom" var="failedReqFrom"/>
    <fmt:message key="error.backPrevPage" var="backPrevPage"/>
    <fmt:message key="error.goMainPage" var="goMainPage"/>
</fmt:bundle>
<mtg:header pageTitle="${pageTitle}"/>
<mtg:menu userRole="${sessionScope.userRole}"/>
<div class="d-flex justify-content-center align-items-center" id="main" style="background-color: wheat;">
    <h1 class="mr-3 pr-3 align-top border-right inline-block align-content-center">${pageContext.errorData.statusCode}</h1>
    <div class="inline-block align-middle">
    <h2 class="font-weight-normal lead" id="desc">${pageContext.errorData.throwable.message}</h2>
        <h2 class="font-weight-normal lead">${failedReqFrom} ${pageContext.errorData.requestURI}</h2>
        <a href="${pageContext.request.getHeader("referer")}" class="btn btn-link">${backPrevPage}</a>
        <a href="main" class="btn btn-link">${goMainPage}</a>
    </div>
</div>
