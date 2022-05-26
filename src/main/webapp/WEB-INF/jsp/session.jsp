<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 26.05.2022
  Time: 19:11
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

<c:set var="session" value="${requestScope.session}"/>
<c:set var="film" value="${session.film}"/>
<c:set var="allSeatList" value="${requestScope.allSeatList}"/>
<c:set var="reservedSeatList" value="${requestScope.reservedSeatList}"/>

<main class="container" data-new-gr-c-s-check-loaded="14.1062.0" data-gr-ext-installed="">
    <%-- SISSON --%>
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
                            </ul>
                            <h4 class="card-text">${ticketPrice}: ${session.ticketPrice} ${currency}</h4>
                        </div>
                        <div>
                            <p>Choose your seat:</p>
                            <form name="seatChoose" method="post" action="main">
                                <input type="hidden" name="command" value="buyTicketPage">
                                <div class="row under-filter">
                                    <div class="col-md-8 under-filter-block px-3">

                                        <c:forEach var="seat" items="${allSeatList}" varStatus="upSeatCounter">
                                            <c:set var="isReserved" value="false"/>
                                            <c:forEach var="reservation" items="${reservedSeatList}">
                                                <c:if test="${reservation.id == seat.id}">
                                                    <c:set var="isReserved" value="true"/>
                                                </c:if>
                                            </c:forEach>


                                            <c:if test="${seat.placeNumber == 1 && upSeatCounter.index > 0}">
                                                <div class="w-100"></div>
                                            </c:if>

                                            <c:choose>
                                                <c:when test="${isReserved == true}">
                                                    <label>
                                                        <input type="checkbox" name="test" class="seatsElement"
                                                               value="${seat.id}" disabled>
                                                        <img class="seatsImg"
                                                             src="<c:url value="/images/svg/seat_gray.svg"/>">
                                                    </label>
                                                </c:when>

                                                <c:otherwise>
                                                    <label>
                                                        <input type="checkbox" name="test" class="seatsElement"
                                                               value="${seat.id}">
                                                        <img class="seatsImg"
                                                             src="<c:url value="/images/svg/seat_blue.svg"/>">
                                                    </label>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                    </div>
                                    <div class="col-md-3 under-filter-block px-3">
                                        <button type="submit" class="btn btn-lg btn-block btn-primary">
                                            ${buyTicket}
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="w-100"></div>

                    <div class="col-md-12">

                    </div>

                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="fragments/footer.jsp"/>
