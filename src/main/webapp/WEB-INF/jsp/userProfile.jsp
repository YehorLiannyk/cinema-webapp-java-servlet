<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 22.05.2022
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>
<%@ taglib prefix="mtg" uri="mytags" %>

<fmt:bundle basename="i18n">
    <fmt:message key="user.pageTitle" var="pageTitle"/>
    <fmt:message key="user.activeTickets" var="activeTickets"/>
    <fmt:message key="user.noActiveTickets" var="noActiveTickets"/>
    <fmt:message key="pagination.prev" var="prev"/>
    <fmt:message key="pagination.next" var="next"/>
</fmt:bundle>

<fmt:bundle basename="i18n">
    <fmt:message key="general.selector.multipleSelectTips" var="selectorTips"/>
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
    <fmt:message key="ticket.sendMail" var="sendMail"/>
    <fmt:message key="ticket.downloadTicket" var="downloadTicket"/>
    <fmt:message key="paying.totalCost" var="totalCostTitle"/>
</fmt:bundle>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<c:set var="ticketList" value="${requestScope.ticketList}"/>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <div class="panel-group" id="accordion">
                <div class="panel panel-primary">
                    <div class="panel-collapse">
                        <h1>${activeTickets}</h1>
                        <div class="row">
                            <div class="col-md-12">
                                <c:choose>

                                    <c:when test="${ticketList.size() != 0}">

                                        <c:forEach var="ticket" items="${ticketList}">
                                            <c:set var="session" value="${ticket.session}"/>
                                            <c:set var="film" value="${session.film}"/>
                                            <c:set var="seat" value="${ticket.seat}"/>

                                            <div class="card w-75 mx-auto my-1">
                                                <div class="row px-4 py-2">
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

                                                    <div class="col-md-6">
                                                        <div class="vertical-buttons-2 w-75">
                                                            <form name="ticketMail" method="get" action="main">
                                                                <input type="hidden" name="command"
                                                                       value="sendTicketViaMail">
                                                                <input type="hidden" name="ticketId"
                                                                       value="${ticket.id}">
                                                                <button type="submit"
                                                                        class="btn btn-lg btn-block btn-outline-secondary my-2">
                                                                        ${sendMail}
                                                                </button>
                                                            </form>
                                                            <form name="ticketPDF" method="get" action="main" target="_blank">
                                                                <input type="hidden" name="command"
                                                                       value="downloadTicket">
                                                                <input type="hidden" name="ticketId"
                                                                       value="${ticket.id}">
                                                                <button type="submit"
                                                                        class="btn btn-lg btn-block btn-primary my-2">
                                                                        ${downloadTicket}
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <div class="w-100"></div>
                                    </c:when>

                                    <c:otherwise>
                                        <span>${noActiveTickets}</span>
                                    </c:otherwise>

                                </c:choose>

                            </div>
                        </div>

                        <mtg:pagination request="${pageContext.request}" totalPages="${requestScope.totalPages}"
                                        prev="${prev}" next="${next}"/>

                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
<ftg:footer/>

