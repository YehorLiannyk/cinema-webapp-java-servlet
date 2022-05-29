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

<fmt:bundle basename="i18n">
    <fmt:message key="user.pageTitle" var="pageTitle"/>
    <fmt:message key="user.activeTickets" var="activeTickets"/>
    <fmt:message key="user.noActiveTickets" var="noActiveTickets"/>
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

                                            <div class="card w-100">
                                                <div class="row p-3">
                                                    <div class="col-md-6">
                                                        <h3>${ticketTitle}: </h3>
                                                        <p>${dateTitle}: ${session.date}</p>
                                                        <p>${time}: ${session.time}</p>
                                                        <p>${seatRowTitle}: ${seat.rowNumber}</p>
                                                        <p>${seatPlaceTitle}: ${seat.placeNumber}</p>
                                                        <p>${filmTitle}: ${film.name}</p>
                                                        <p>${duration}: ${film.getDurationInMinutes()} ${durationPostfix}</p>
                                                        <p>${costTitle}: ${session.ticketPrice} ${currency}</p>
                                                    </div>

                                                    <div class="col-md-3">
                                                        <div class="vertical-buttons">
                                                            <form name="ticketMail" method="post" action="main">
                                                                <input type="hidden" name="command"
                                                                       value="sendTicketViaMail">
                                                                <input type="hidden" name="ticketId"
                                                                       value="${ticket.id}">
                                                                <button type="submit"
                                                                        class="btn btn-lg btn-block btn-primary my-2">
                                                                        ${sendMail}
                                                                </button>
                                                            </form>
                                                            <form name="ticketPDF" method="post" action="main" target="_blank">
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
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
<ftg:footer/>

