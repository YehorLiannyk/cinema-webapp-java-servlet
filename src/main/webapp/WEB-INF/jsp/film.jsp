<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 26.05.2022
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>
<%@ taglib prefix="mtg" uri="mytags" %>

<fmt:bundle basename="i18n">
    <fmt:message key="film.filmPage" var="pageTitle"/>
    <fmt:message key="session.seatsRemain" var="seatsRemain"/>
    <fmt:message key="film.duration" var="duration"/>
    <fmt:message key="film.duration.postfix" var="durationPostfix"/>
    <fmt:message key="film.genres" var="genres"/>
    <fmt:message key="film.description" var="description"/>
</fmt:bundle>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<c:set var="film" value="${requestScope.film}"/>

<main class="container" data-new-gr-c-s-check-loaded="14.1062.0" data-gr-ext-installed="">
    <div class="container-fluid">
        <h1>${pageTitle}</h1>
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-3">
                        <img class="poster-img card-img" src="${film.posterUrl}">
                    </div>
                    <div class="col-md-9">
                        <div>
                            <h2 class="card-title">${film.name}</h2>
                            <ul class="list-unstyled mt-3 mb-4">
                                <li class="card-text">${genres}:
                                    <mtg:filmGenresList film="${film}"/>
                                </li>
                                <li class="card-text">
                                    ${duration}: ${film.getDurationInMinutes()} ${durationPostfix}
                                </li>
                                <li class="card-text py-1">
                                    <c:if test="${film.description != null && film.description != ''}">
                                        <h6>${description}</h6>
                                        <p>${film.description}</p>
                                    </c:if>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<ftg:footer/>
