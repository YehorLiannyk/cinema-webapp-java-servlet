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
    <fmt:message key="film.goToFilmPage" var="filmPage"/>
    <fmt:message key="mainpage.carousel.title" var="carouselTitle"/>
    <fmt:message key="mainpage.carousel.text" var="carouselText"/>
    <fmt:message key="pagination.next" var="next"/>
    <fmt:message key="pagination.prev" var="prev"/>
</fmt:bundle>

<c:url value="/main?command=filmPage" var="filmPage_url"/>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<div class="container">
    <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark main-carousel">
        <div class="col-md-6 px-3 main-carousel-text">
            <h1 class="display-4 font-italic">${carouselTitle}</h1>
            <p class="lead my-3 p-4">${carouselText}</p>
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

                <table>
                    <thead>
                    </thead>
                    <tbody>
                    <c:forEach var="film" items="${sessionScope.filmList}" varStatus="counter">
                        <c:if test="${counter.index % 2 == 0}">
                            <tr>
                            <td>
                            <div class="row">
                        </c:if>
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
                                    <form name="film" method="get" action="main">
                                        <input type="hidden" name="command"
                                               value="filmPage">
                                        <input type="hidden" name="filmId"
                                               value="${film.id}">
                                        <button type="submit"
                                                class="btn btn-lg btn-block btn-primary w-75">
                                                ${filmPage}
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <c:if test="${counter.index %2 != 0 && counter.index != 0}">
                            </div>
                            </td>
                            </tr>
                        </c:if>

                    </c:forEach>
                    </tbody>
                </table>

                <mtg:pagination request="${pageContext.request}" totalPages="${requestScope.totalPages}" prev="${prev}"
                                next="${next}"/>

            </div>
        </div>
    </div>
</main>
<ftg:footer/>