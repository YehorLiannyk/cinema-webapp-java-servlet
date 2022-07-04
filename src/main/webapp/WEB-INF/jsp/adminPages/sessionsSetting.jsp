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
    <fmt:message key="general.currency.short" var="currency"/>
    <fmt:message key="admin.sessionsSetting.pageTitle" var="pageTitle"/>
    <fmt:message key="session.time" var="time"/>
    <fmt:message key="session.timePrefix" var="timePrefix"/>
    <fmt:message key="session.seatsRemain" var="seatsRemain"/>
    <fmt:message key="session.ticketPrice" var="ticketPrice"/>
    <fmt:message key="session.buyTicket" var="buyTicket"/>
    <fmt:message key="schedule.listTitle" var="sessionsTitle"/>
    <fmt:message key="film.duration" var="duration"/>
    <fmt:message key="film.duration.postfix" var="durationPostfix"/>
    <fmt:message key="film.genres" var="genres"/>
    <fmt:message key="admin.sessionsSetting.delete" var="delete"/>
    <fmt:message key="admin.sessionsSetting.sureWantDelete" var="sureWantDelete"/>
    <fmt:message key="admin.sessionsSetting.sessionInfo" var="sessionInfo"/>
    <fmt:message key="admin.sessionsSetting.filmDeleting" var="sessionDeleting"/>
    <fmt:message key="pagination.prev" var="prev"/>
    <fmt:message key="pagination.next" var="next"/>
</fmt:bundle>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <div class="container-fluid">
                <h1>${pageTitle}</h1>
                <div class="row">
                    <div class="col-md-12">
                        <div class="film-posts py-4">
                            <table>
                                <thead>
                                </thead>
                                <tbody>
                                <c:forEach var="session" items="${requestScope.sessionList}">
                                    <c:set var="film" value="${session.film}"/>
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
                                                            <li class="card-text">
                                                                    ${time}: ${session.date} ${timePrefix} ${session.time}
                                                            </li>
                                                            <li class="card-text">
                                                                    ${seatsRemain}: ${session.seatsAmount}
                                                            </li>
                                                        </ul>
                                                        <h5 class="card-text">${ticketPrice}: ${session.ticketPrice} ${currency}</h5>
                                                    </div>

                                                    <div class="col-md-3">
                                                        <div class="vertical-buttons-4">
                                                            <form name="session" method="post" action="main">
                                                                <input type="hidden" name="command"
                                                                       value="sessionInfoPage">
                                                                <input type="hidden" name="sessionId"
                                                                       value="${session.id}">
                                                                <button type="submit"
                                                                        class="btn btn-lg btn-block btn-primary my-2">
                                                                        ${sessionInfo}
                                                                </button>
                                                            </form>
                                                            <button type="button"
                                                                    class="btn btn-lg btn-block btn-danger"
                                                                    data-toggle="modal"
                                                                    data-target="#modal${session.id}">
                                                                    ${delete}
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>

                                    <!-- Modal -->
                                    <div class="modal fade" id="modal${session.id}" tabindex="-1"
                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title"
                                                        id="exampleModalLabel">${sessionDeleting}</h5>
                                                </div>
                                                <div class="modal-body">${sureWantDelete}
                                                </div>
                                                <div class="modal-footer">
                                                    <form name="session" method="post"
                                                          action="main">
                                                        <input type="hidden" name="command"
                                                               value="deleteSession">
                                                        <input type="hidden" name="sessionId"
                                                               value="${session.id}">
                                                        <button type="submit"
                                                                class="btn btn-primary">${delete}
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                </tbody>
                            </table>

                            <mtg:pagination request="${pageContext.request}" totalPages="${requestScope.totalPages}"
                                            prev="${prev}" next="${next}"/>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<ftg:footer/>