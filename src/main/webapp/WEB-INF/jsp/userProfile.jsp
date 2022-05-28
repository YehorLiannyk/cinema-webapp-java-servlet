<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 22.05.2022
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mtg" %>

<fmt:bundle basename="i18n">
    <fmt:message key="user.pageTitle" var="pageTitle"/>
    <fmt:message key="user.activeTickets" var="activeTickets"/>
    <fmt:message key="user.noActiveTickets" var="noActiveTickets"/>
</fmt:bundle>

<fmt:bundle basename="i18n">
    <fmt:message key="general.selector.multipleSelectTips" var="selectorTips"/>
</fmt:bundle>

<mtg:header pageTitle="${pageTitle}"/>
<mtg:menu userRole="${sessionScope.userRole}"/>

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
<mtg:footer/>

