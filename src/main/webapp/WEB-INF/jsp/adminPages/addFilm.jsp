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


<fmt:bundle basename="i18n" prefix="admin.addFilm.">
    <fmt:message key="pageTitle" var="pageTitle"/>
    <fmt:message key="filmName" var="filmName"/>
    <fmt:message key="filmDescription" var="filmDescription"/>
    <fmt:message key="genres" var="genres"/>
    <fmt:message key="poster" var="poster"/>
    <fmt:message key="posterPlaceholder" var="posterPlaceholder"/>
    <fmt:message key="duration" var="duration"/>
    <fmt:message key="durationPlaceholder" var="durationPlaceholder"/>
    <fmt:message key="filmUpload" var="filmUpload"/>
    <fmt:message key="uploadBtn" var="uploadBtn"/>
</fmt:bundle>

<fmt:bundle basename="i18n">
    <fmt:message key="general.selector.multipleSelectTips" var="selectorTips"/>
    <fmt:message key="valid.url.empty" var="urlEmpty"/>
    <fmt:message key="valid.url.length" var="urlLength"/>
</fmt:bundle>

<fmt:bundle basename="i18n" prefix="valid.">
    <fmt:message key="onlyDigits" var="onlyDigits"/>
    <fmt:message key="film.name.empty" var="filmNameEmpty"/>
    <fmt:message key="film.name.length" var="filmNameLength"/>
    <fmt:message key="film.desc.length" var="filmDescLength"/>
    <fmt:message key="film.duration.empty" var="filmDurationEmpty"/>
    <fmt:message key="film.duration.range" var="filmDurationRange"/>
    <fmt:message key="film.genreList.empty" var="genreListEmpty"/>
</fmt:bundle>

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
                            <input type="hidden" name="command" value="addFilm">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="filmName"
                                               placeholder="${filmName}" required
                                                <c:if test="${pageContext.request.getParameter('filmName') != null}">
                                                    value="${pageContext.request.getParameter('filmName')}"
                                                </c:if>/>

                                        <c:if test="${requestScope.filmName_length}">
                                            <p class="form-error">${filmNameLength}</p>
                                        </c:if>
                                        <c:if test="${requestScope.filmName_empty}">
                                            <p class="form-error">${filmNameEmpty}</p>
                                        </c:if>

                                    </div>
                                    <div class="form-group">
                                        <textarea class="form-control" name="filmDescription"
                                                  placeholder="${filmDescription}" rows="5"><c:if test="${pageContext.request.getParameter('filmDescription') != null}">${pageContext.request.getParameter('filmDescription')}</c:if></textarea>
                                        <c:if test="${requestScope.filmDesc_length}">
                                            <p class="form-error">${filmDescLength}</p>
                                        </c:if>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="genres">${genres}</label>
                                        <select name="genreIds" id="genres" class="form-control" multiple required>
                                            <c:forEach var="genre" items="${requestScope.genreList}">
                                                <option value="${genre.id}">${genre.name}</option>
                                            </c:forEach>
                                        </select>
                                        <small id="selectTips" class="form-text text-muted">${selectorTips}</small>
                                        <c:if test="${requestScope.genreList_empty}">
                                            <p class="form-error">${genreListEmpty}</p>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="poster-url">${poster}</label>
                                        <input type="text" class="form-control" id="poster-url"
                                                <c:if test="${pageContext.request.getParameter('posterUrl') != null}">
                                                    value="${pageContext.request.getParameter('posterUrl')}"
                                                </c:if>
                                               name="posterUrl" placeholder="${posterPlaceholder}" required/>
                                        <c:if test="${requestScope.url_length}">
                                            <p class="form-error">${urlLength}</p>
                                        </c:if>
                                        <c:if test="${requestScope.url_empty}">
                                            <p class="form-error">${urlEmpty}</p>
                                        </c:if>
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <div class="form-group">
                                                    <label for="duration">${duration}</label>
                                                    <input type="text" class="form-control" id="duration"
                                                            <c:if test="${pageContext.request.getParameter('filmDuration') != null}">
                                                                value="${pageContext.request.getParameter('filmDuration')}"
                                                            </c:if>
                                                           name="filmDuration" placeholder="${durationPlaceholder}"
                                                           required/>
                                                    <c:if test="${requestScope.duration_range}">
                                                        <p class="form-error">${filmDurationRange}</p>
                                                    </c:if>
                                                    <c:if test="${requestScope.duration_empty}">
                                                        <p class="form-error">${filmDurationEmpty}</p>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>${filmUpload}</label>
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