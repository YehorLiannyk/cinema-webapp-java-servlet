<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 24.05.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>
<fmt:bundle basename="i18n" prefix="admin.addFilm.">
    <fmt:message key="pageTitle" var="pageTitle"/>
    <fmt:message key="filmName" var="filmName"/>
    <fmt:message key="filmDescription" var="filmDescription"/>
    <fmt:message key="genres" var="genres"/>
    <fmt:message key="genresTips" var="genresTips"/>
    <fmt:message key="poster" var="poster"/>
    <fmt:message key="posterPlaceholder" var="posterPlaceholder"/>
    <fmt:message key="duration" var="duration"/>
    <fmt:message key="durationPlaceholder" var="durationPlaceholder"/>
    <fmt:message key="filmUpload" var="filmUpload"/>
    <fmt:message key="uploadBtn" var="uploadBtn"/>
</fmt:bundle>

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
                                               placeholder="${filmName}" required/>
                                    </div>
                                    <div class="form-group">
                                        <textarea class="form-control" name="filmDescription"
                                                  placeholder="${filmDescription}" rows="5"></textarea>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="genres">${genres}</label>
                                        <select name="genres" id="genres" class="form-control" multiple required>
                                            <c:forEach var="genre" items="${requestScope.genreList}">
                                                <option value="${genre.id}">${genre.name}</option>
                                            </c:forEach>
                                        </select>
                                        <small id="selectTips" class="form-text text-muted">${genresTips}</small>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="poster-url">
                                            Poster</label>
                                        <input type="text" class="form-control" id="poster-url"
                                               name="postUrl" placeholder="Poster URL" required/>
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <div class="form-group">
                                                    <label for="duration">${duration}</label>
                                                    <input type="text" class="form-control" id="duration"
                                                           name="filmDuration" placeholder="${durationPlaceholder}"
                                                           required/>
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
<jsp:include page="../fragments/footer.jsp"/>