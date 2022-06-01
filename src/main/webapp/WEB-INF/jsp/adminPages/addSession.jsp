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

<fmt:bundle basename="i18n" prefix="admin.addSession.">
    <fmt:message key="pageTitle" var="pageTitle"/>
    <fmt:message key="films" var="films"/>
    <fmt:message key="filmTips" var="filmTips"/>
    <fmt:message key="date" var="date"/>
    <fmt:message key="time" var="time"/>
    <fmt:message key="timeMinTips" var="timeMinTips"/>
    <fmt:message key="timeMaxTips" var="timeMaxTips"/>
    <fmt:message key="ticketPrice" var="ticketPrice"/>
    <fmt:message key="ticketPricePlaceholder" var="ticketPricePlaceholder"/>
    <fmt:message key="sessionUpload" var="sessionUpload"/>
    <fmt:message key="uploadBtn" var="uploadBtn"/>
</fmt:bundle>

<c:set var="minTime" value="${applicationScope.minSessionTime}"/>
<c:set var="maxTime" value="${applicationScope.maxSessionTime}"/>
<c:set var="nowDate" value="${sessionScope.nowDate}"/>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <h1 class="">${pageTitle}</h1>
            <div class="panel-group" id="accordion">
                <div class="panel panel-primary">
                    <div class="panel-collapse">
                        <form class="panel-body" name="command" method="post" action="main">
                            <input type="hidden" name="command" value="addSession">
                            <div class="row">

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="film">${films}</label>
                                        <select name="filmId" id="film" class="form-control" required>
                                            <c:forEach var="film" items="${requestScope.filmList}">
                                                <option value="${film.id}">${film.name}</option>
                                            </c:forEach>
                                        </select>
                                        <small id="selectTips" class="form-text text-muted">${filmTips}</small>
                                    </div>

                                    <div class="form-group">
                                        <label for="date">${date}</label>
                                        <input type="date" class="form-control" id="date" name="date" min="${nowDate}"
                                               required/>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="time">${time}</label>
                                        <input type="time" class="form-control" id="time" name="time" min="${minTime}"
                                               max="${maxTime}" required/>
                                        <small id="timeTips" class="form-text text-muted">
                                            ${timeMinTips} ${minTime} ${timeMaxTips} ${maxTime}
                                        </small>
                                    </div>

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <div class="form-group">
                                                    <label for="ticket">${ticketPrice}</label>
                                                    <input type="number" min="0" class="form-control" id="ticket"
                                                           name="ticketPrice" placeholder="${ticketPricePlaceholder}"
                                                           required/>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>${sessionUpload}</label>
                                                    <button type="submit" class="btn btn-success btn-sm form-control">
                                                        <span class="glyphicon glyphicon-floppy-disk">${uploadBtn}</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

</main>
<ftg:footer/>