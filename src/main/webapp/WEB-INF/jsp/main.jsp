<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 18.05.2022
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>
<%@ taglib prefix="mtg" uri="mytags" %>

<fmt:bundle basename="i18n">
    <fmt:message key="mainPage.title" var="pageTitle"/>
    <fmt:message key="filmList.title" var="title"/>
    <fmt:message key="film.duration.postfix" var="durationPostfix"/>
    <fmt:message key="film.duration" var="duration"/>
    <fmt:message key="film.genres" var="genres"/>
    <fmt:message key="film.aboutFilm" var="aboutFilm"/>
</fmt:bundle>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<div class="container">
    <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark main-carousel">
        <div class="col-md-6 px-3 main-carousel-text">
            <h1 class="display-4 font-italic">Your CINEMA</h1>
            <p class="lead my-3">Кінотеатр, що створений для повного занурення у світ кіно.
                Тут найкращі технології, високий рівень комфорту, чудовий сервіс та найхрусткіших попкорн!</p>
        </div>
    </div>
</div>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <h1 class="">
                ${title}
            </h1>
            <div class="film-posts py-4">
                <div class="row">

                    <c:forEach var="film" items="${sessionScope.filmList}">
                        <div class="col-md-6 p-4 film-post card">
                            <div class="row card-body">
                                <div class="col-md-4">
                                    <img class="poster-img card-img" src="${film.posterUrl}">
                                </div>
                                <div class="col-md-8">
                                    <h2 class="card-title">${film.name}</h2>
                                    <ul class="list-unstyled mt-3 mb-4">
                                        <li class="card-text">
                                                ${duration}: ${film.getDurationInMinutes()} ${durationPostfix}
                                        </li>
                                        <li class="card-text"> ${genres}:
                                            <mtg:filmGenresList film="${film}"/>
                                        </li>
                                    </ul>
                                    <button type="button" class="btn btn-lg btn-block btn-primary">
                                            ${aboutFilm}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <nav class="blog-pagination">
                <a class="btn btn-outline-primary" href="#">Older</a>
                <a class="btn btn-outline-secondary disabled" href="#">Newer</a>
            </nav>
        </div>
    </div>
</main>
<ftg:footer/>