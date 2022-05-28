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
<fmt:setBundle basename="i18n"/>
<fmt:bundle basename="i18n">
    <fmt:message key="admin.filmsSetting.pageTitle" var="pageTitle"/>
</fmt:bundle>
<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <h1 class="">
                <fmt:message key="filmList.title"/>
            </h1>
            <div class="film-posts py-4">
                <div class="row">
                    <jsp:useBean id="filmList" scope="session" type="java.util.List"/>
                    <c:forEach var="film" items="${filmList}" varStatus="counter">
                        <div class="col-md-6 p-4 film-post card">
                            <div class="row card-body">
                                <div class="col-md-4">
                                    <img class="poster-img card-img" src="${film.posterUrl}">
                                </div>
                                <div class="col-md-8">
                                    <h2 class="card-title">${film.name}</h2>
                                    <ul class="list-unstyled mt-3 mb-4">
                                        <li class="card-text">
                                            <fmt:message key="film.duration"/>: ${film.getDurationInMinutes()}
                                            <fmt:message key="film.duration.postfix"/>
                                        </li>
                                        <li class="card-text"><fmt:message key="film.genres"/>:
                                            <mtg:filmGenresList film="${film}"/>
                                        </li>
                                    </ul>
                                    <button type="button" class="btn btn-lg btn-block btn-primary">
                                        <fmt:message key="film.buyTicket"/>
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
