<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 27.05.2022
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<fmt:bundle basename="i18n">
    <fmt:message key="general.currency.short" var="currency"/>
    <fmt:message key="session.time" var="time"/>
    <fmt:message key="session.timePrefix" var="timePrefix"/>
    <fmt:message key="session.seatsRemain" var="seatsRemain"/>
    <fmt:message key="session.ticketPrice" var="ticketPrice"/>
    <fmt:message key="session.buyTicket" var="buyTicket"/>
    <fmt:message key="film.duration" var="duration"/>
    <fmt:message key="film.duration.postfix" var="durationPostfix"/>
    <fmt:message key="ticket.cost" var="costTitle"/>
    <fmt:message key="ticket.film" var="filmTitle"/>
    <fmt:message key="ticket.ticketTitle" var="ticketTitle"/>
    <fmt:message key="ticket.seatRow" var="seatRowTitle"/>
    <fmt:message key="ticket.seatPlace" var="seatPlaceTitle"/>
    <fmt:message key="ticket.payBtn" var="payBtn"/>
    <fmt:message key="paying.pageTitle" var="pageTitle"/>
    <fmt:message key="ticket.date" var="dateTitle"/>
    <fmt:message key="paying.totalCost" var="totalCostTitle"/>
</fmt:bundle>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<c:set var="ticketList" value="${sessionScope.ticketList}"/>
<c:set var="totalCost" value="${requestScope.totalCost}"/>


<main class="container" data-new-gr-c-s-check-loaded="14.1062.0" data-gr-ext-installed="">
    <div class="container-fluid">
        <h1>${pageTitle}</h1>
        <div class="row">
            <div class="col-md-12">
                <c:forEach var="ticket" items="${ticketList}">
                    <c:set var="session" value="${ticket.session}"/>
                    <c:set var="film" value="${session.film}"/>
                    <c:set var="seat" value="${ticket.seat}"/>

                    <div class="px-4 py-3 film-post card w-100">
                        <div class="col-md-6">
                            <h3>${ticketTitle}: </h3>
                            <h6>${dateTitle}: ${session.date}</h6>
                            <h6>${time}: ${session.time}</h6>
                            <h6>${seatRowTitle}: ${seat.rowNumber}</h6>
                            <h6>${seatPlaceTitle}: ${seat.placeNumber}</h6>
                            <h6>${filmTitle}: ${film.name}</h6>
                            <h6>${duration}: ${film.getDurationInMinutes()} ${durationPostfix}</h6>
                            <h5>${costTitle}: ${session.ticketPrice} ${currency}</h5>
                        </div>
                    </div>
                </c:forEach>
                <div class="w-100"></div>
                <br/>
                <h2 style="text-align: right">${totalCostTitle}: ${totalCost}</h2>
                <form name="seatIds" method="post" action="main">
                    <input type="hidden" name="command" value="buyTicket">
                    <button type="submit" class="btn btn-success w-25" style="float: right;">${payBtn}</button>
                </form>
            </div>
        </div>
    </div>
</main>
<ftg:footer/>

