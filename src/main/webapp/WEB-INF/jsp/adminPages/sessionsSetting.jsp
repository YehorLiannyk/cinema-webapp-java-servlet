<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 24.05.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>

<fmt:bundle basename="i18n">
    <fmt:message key="general.currency.short" var="currency"/>
    <fmt:message key="schedule.title" var="pageTitle"/>
    <fmt:message key="session.time" var="time"/>
    <fmt:message key="session.timePrefix" var="timePrefix"/>
    <fmt:message key="session.seatsRemain" var="seatsRemain"/>
    <fmt:message key="session.ticketPrice" var="ticketPrice"/>
    <fmt:message key="session.buyTicket" var="buyTicket"/>
    <fmt:message key="film.duration" var="duration"/>
    <fmt:message key="film.duration.postfix" var="durationPostfix"/>
    <fmt:message key="film.genres" var="genres"/>
    <fmt:message key="general.selector.multipleSelectTips" var="selectorTips"/>
    <fmt:message key="film.aboutFilm" var="aboutFilm"/>
</fmt:bundle>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <%-- SISSONS --%>
            <div class="container-fluid">
                <h1>${pageTitle}</h1>
                <div class="row">
                    <div class="col-md-12">
                        <div class="film-posts py-4">

                            <c:forEach var="session" items="${requestScope.sessionList}">
                                <c:set var="film" value="${session.getFilm()}"/>
                                <div class="p-4 film-post card w-100">
                                    <div class="row card-body">
                                        <div class="col-md-2">
                                            <img class="poster-img card-img" src="${film.posterUrl}">
                                        </div>
                                        <div class="col-md-7">
                                            <h2 class="card-title">${film.name}</h2>
                                            <ul class="list-unstyled mt-3 mb-4">
                                                <li class="card-text">${genres}:
                                                    <c:forEach var="genre" items="${film.genreList}"
                                                               varStatus="counter">
                                                        <c:if test="${counter.index < (film.genreList.size() - 1)}">
                                                            <c:out value="${genre.name},"/>
                                                        </c:if>
                                                        <c:if test="${counter.index == (film.genreList.size() - 1)}">
                                                            <c:out value="${genre.name}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </li>
                                                <li class="card-text">
                                                        ${duration}: ${film.getDurationInMinutes()} ${durationPostfix}
                                                </li>
                                                <li class="card-text">
                                                        ${time}: ${session.date} ${timePrefix} ${session.time}
                                                </li>
                                                <li class="card-text">
                                                        ${seatsRemain}: ###
                                                </li>
                                            </ul>
                                            <h5 class="card-text">${ticketPrice}: ${session.ticketPrice} ${currency}</h5>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="vertical-buttons">
                                                <button type="button" class="btn btn-lg btn-block btn-primary">
                                                        ${aboutFilm}
                                                </button>
                                                <button type="button" class="btn btn-lg btn-block btn-primary">
                                                        ${buyTicket}
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <nav class="blog-pagination pagination justify-content-center">
                <a class="btn btn-outline-primary" href="#">Older</a>
                <a class="btn btn-outline-secondary disabled" href="#">Newer</a>
            </nav>
        </div>
    </div>
</main>
<jsp:include page="../fragments/footer.jsp"/>