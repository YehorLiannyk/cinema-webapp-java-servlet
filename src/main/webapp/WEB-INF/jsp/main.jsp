<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 18.05.2022
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/menu.jsp"/>
<div class="container">
    <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
        <div class="col-md-6 px-0">
            <h1 class="display-4 font-italic">Title of a longer featured blog post</h1>
            <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and
                efficiently about what's most interesting in this post's contents.</p>
            <p class="lead mb-0"><a href="#" class="text-white font-weight-bold">Continue reading...</a></p>
        </div>
    </div>
</div>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <h1 class="">
                <fmt:message key="filmList.title"/>
            </h1>
            <div class="film-posts py-4">
                <div class="row">

                    <c:forEach var="film" items="${sessionScope.filmList}">
                        <div class="col-md-6 p-4 film-post card">
                            <div class="row card-body">
                                <div class="col-md-4">
                                    <img class="poster-img card-img" src="${film.posterUrl}">
                                </div>
                                <div class="col-md-8">
                                    <h2 class="card-title">${film.name}</h2>
                                    <ul class="list-unstyled mt-3 mb-4">
                                        <li class="card-text">
                                            <fmt:message key="film.duration"/>: ${film.getDurationInMinutes()}
                                            <fmt:message key="film.duration.postfix"/>
                                        </li>
                                        <li class="card-text"><fmt:message key="film.genres"/>:
                                            <c:forEach var="genre" items="${film.genreList}" varStatus="counter">
                                                <c:if test="${counter.index < (film.genreList.size() - 1)}">
                                                    <c:out value="${genre.name},"/>
                                                </c:if>
                                                <c:if test="${counter.index == (film.genreList.size() - 1)}">
                                                    <c:out value="${genre.name}"/>
                                                </c:if>
                                            </c:forEach>
                                        </li>
                                        <li class="card-text"><fmt:message key="film.sessionAmount"/>: ###</li>
                                    </ul>
                                    <button type="button" class="btn btn-lg btn-block btn-primary">
                                        <fmt:message key="film.aboutFilm"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <nav class="blog-pagination">
                <a class="btn btn-outline-primary" href="#">Older</a>
                <a class="btn btn-outline-secondary disabled" href="#">Newer</a>
            </nav>
        </div>
    </div>
</main>
<jsp:include page="fragments/footer.jsp"/>