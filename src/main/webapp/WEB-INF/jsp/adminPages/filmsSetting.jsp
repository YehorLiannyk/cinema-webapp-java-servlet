<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 24.05.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>
<%@ taglib prefix="mtg" uri="mytags" %>
<fmt:bundle basename="i18n">
    <fmt:message key="admin.filmsSetting.pageTitle" var="pageTitle"/>
    <fmt:message key="filmList.title" var="title"/>
    <fmt:message key="film.duration.postfix" var="durationPostfix"/>
    <fmt:message key="film.duration" var="duration"/>
    <fmt:message key="film.goToFilmPage" var="goToFilmPage"/>
    <fmt:message key="film.genres" var="genres"/>
    <fmt:message key="admin.filmsSetting.delete" var="delete"/>
    <fmt:message key="admin.filmsSetting.sureWantDelete" var="sureWantDelete"/>
    <fmt:message key="admin.filmsSetting.filmDeleting" var="filmDeleting"/>
</fmt:bundle>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <h1 class="">
                ${pageTitle}
            </h1>
            <div class="film-posts py-4">
                <div class="row">
                    <table id="pagination_table" class="table table-striped">
                        <thead>
                        </thead>
                        <tbody>
                        <jsp:useBean id="filmList" scope="session" type="java.util.List"/>
                        <c:forEach var="film" items="${filmList}" varStatus="counter">
                            <tr>
                                <td>
                                    <div class="p-4 film-post card w-100">
                                        <div class="row card-body">
                                            <div class="col-md-2">
                                                <img class="poster-img card-img" src="${film.posterUrl}">
                                            </div>
                                            <div class="col-md-7">
                                                <h2 class="card-title">${film.name}</h2>
                                                <ul class="list-unstyled mt-3 mb-4">
                                                    <li class="card-text">${genres}:
                                                        <mtg:filmGenresList film="${film}"/>
                                                    </li>
                                                    <li class="card-text">
                                                            ${duration}: ${film.getDurationInMinutes()} ${durationPostfix}
                                                    </li>
                                                </ul>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="vertical-buttons">
                                                    <form name="film" method="post" action="main">
                                                        <input type="hidden" name="command"
                                                               value="filmPage">
                                                        <input type="hidden" name="filmId"
                                                               value="${film.id}">
                                                        <button type="submit"
                                                                class="btn btn-lg btn-block btn-primary my-2">
                                                                ${goToFilmPage}
                                                        </button>
                                                    </form>
                                                    <span>id : ${film.id}</span>
                                                    <button type="button"
                                                            class="btn btn-lg btn-block btn-danger"
                                                            data-toggle="modal" data-target="#modal${film.id}">
                                                            ${delete}
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Modal -->
                                    <div class="modal fade" id="modal${film.id}" tabindex="-1"
                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title"
                                                        id="exampleModalLabel">${filmDeleting}</h5>
                                                </div>
                                                <div class="modal-body">${sureWantDelete}
                                                </div>
                                                <div class="modal-footer">
                                                    <form name="film" method="post"
                                                          action="main">
                                                        <input type="hidden" name="command"
                                                               value="deleteFilm">
                                                        <input type="hidden" name="filmId"
                                                               value="${film.id}">
                                                        <span>id : ${film.id}</span>
                                                        <button type="submit"
                                                                class="btn btn-primary">${delete}
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>
<ftg:footer/>
