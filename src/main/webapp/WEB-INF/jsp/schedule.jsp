<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 18.05.2022
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/menu.jsp"/>

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

<main class="container" data-new-gr-c-s-check-loaded="14.1062.0" data-gr-ext-installed="">

    <div class="filter-sorter">
        <div class="row">
            <%--  Filter  --%>
            <aside class="col-md-3">
                <header class="card-header-custom">
                    <h5 class="card-custom-title">Filter</h5>
                </header>
                <form name="filtration" method="post" action="main">
                    <input type="hidden" name="command" value="schedule">
                    <input type="hidden" name="filtered" value="true">
                    <div class="card-group">
                        <article class="card card-filter">
                            <div class="filter-content">
                                <div class="card-body">
                                    <h5 class="card-title">Show only</h5>
                                    <label class="form-check">
                                        <input class="form-check-input" type="radio" checked="checked"
                                               name="all" value="">
                                        <span class="form-check-label">All</span>
                                    </label>
                                    <label class="form-check">
                                        <input class="form-check-input" type="radio" name="onlyAvailable" value="">
                                        <span class="form-check-label">Only available</span>
                                    </label>
                                    <button type="submit" class="btn btn-primary w-100 btn-sorter">Submit</button>
                                </div> <!-- card-body.// -->
                            </div>
                        </article> <!-- card-group-item.// -->

                    </div> <!-- card.// -->
                </form>
            </aside> <!-- col.// -->

            <%--    Sorter   --%>
            <aside class="col-md-4">
                <header class="card-header-custom">
                    <h5 class="card-custom-title">Sorter</h5>
                </header>
                <form name="sorter" method="post" action="main">
                    <input type="hidden" name="command" value="schedule">
                    <input type="hidden" name="sorted" value="true">
                    <div class="card-group">
                        <article class="card card-sorter">
                            <div class="filter-content">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h5 class="card-title">Sort by</h5>
                                            <div class="form-group">
                                                <select name="sortBy" id="sortBy" class="form-control"
                                                        style="min-width: 150px;" required>
                                                    <option value="dateTimeSortBy" selected>Date and time</option>
                                                    <option value="filmNameSortBy">Film name</option>
                                                    <option value="seatsRemainSortBy">Seats remain</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <h5 class="card-title">Sort method</h5>
                                            <div class="form-group">
                                                <select name="sortMethod" id="sortMethod" class="form-control"
                                                        style="min-width: 150px;"
                                                        required>
                                                    <option value="ascendingSortMethod" selected>Ascending</option>
                                                    <option value="descendingSortMethod">Descending</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="w-100"></div>
                                        <div class="col">
                                            <button type="submit" class="btn btn-primary w-50 btn-sorter">Submit
                                            </button>
                                        </div>
                                    </div>
                                </div> <!-- card-body.// -->
                            </div>
                        </article> <!-- card-group-item.// -->
                    </div> <!-- card.// -->
                </form>
            </aside> <!-- col.// -->
        </div> <!-- row.// -->

        <div class="row p-2 under-filter">
            <div class="col-md-2 under-filter-block px-2">
                <p class="font-weight-light under-filter-element">Seleted: ##</p>
            </div>
            <div class="col-md-5 under-filter-block px-2">
                <div class="row">
                    <div class="col-md-3 under-filter-element">
                        <button type="submit" class="btn btn-outline-dark btn-sm">Reset filters</button>
                    </div>
                    <div class="col-md-8 under-filter-element" style="display: initial;">
                        <label for="elementsOnPage" class="font-weight-light under-filter-element">Show elements on
                            page:</label>
                        <input id="elementsOnPage" type="number"/>
                        <button type="submit" class="btn btn-outline-dark btn-sm" style="vertical-align: bottom;">
                            Set
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

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
                                            <c:forEach var="genre" items="${film.genreList}" varStatus="counter">
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
                                        <form name="session" method="post" action="main">
                                            <input type="hidden" name="command" value="sessionPage">
                                            <input type="hidden" name="sessionId" value="${session.id}">
                                            <button type="submit" class="btn btn-lg btn-block btn-primary">
                                                    ${buyTicket}
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <nav class="blog-pagination">
                    <a class="btn btn-outline-primary" href="#">Older</a>
                    <a class="btn btn-outline-secondary disabled" href="#">Newer</a>
                </nav>
            </div>
        </div>
    </div>
</main>
<jsp:include page="fragments/footer.jsp"/>



