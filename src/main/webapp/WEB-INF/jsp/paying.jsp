<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 27.05.2022
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtg" %>

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

<mtg:header pageTitle="${pageTitle}"/>
<mtg:menu userRole="${sessionScope.userRole}"/>

<c:set var="ticketList" value="${sessionScope.ticketList}"/>
<c:set var="totalCost" value="${requestScope.totalCost}"/>


<main class="container" data-new-gr-c-s-check-loaded="14.1062.0" data-gr-ext-installed="">
    <div class="container-fluid">
        <h1>Tickets info:</h1>
        <div class="row">
            <div class="col-md-12">
                <c:forEach var="ticket" items="${ticketList}">
                    <c:set var="session" value="${ticket.session}"/>
                    <c:set var="film" value="${session.film}"/>
                    <c:set var="seat" value="${ticket.seat}"/>

                    <div class="card w-100">
                        <div class="col-md-6">
                            <h3>Ticket: </h3>
                            <p>Date: ${session.date}</p>
                            <p>Time: ${session.time}</p>
                            <p>Seat row: ${seat.rowNumber}</p>
                            <p>Seat place: ${seat.placeNumber}</p>
                            <p>Film: ${film.name}</p>
                            <p>Duration: ${film.getDurationInMinutes()}</p>
                            <p>Cost: ${session.ticketPrice}</p>
                        </div>
                    </div>
                </c:forEach>
                <div class="w-100"></div>
                <br/>
                <h2 style="text-align: right">Total cost: ${totalCost}</h2>
                <form name="seatIds" method="post" action="main">
                    <input type="hidden" name="command" value="buyTicket">
                    <button type="submit" class="btn btn-success w-25" style="float: right;">Pay</button>
                </form>
            </div>
        </div>
    </div>
</main>
<mtg:footer/>

