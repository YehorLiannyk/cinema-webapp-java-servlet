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
<jsp:include page="fragments/header.jsp" />
<jsp:include page="fragments/menu.jsp" />
<div class="container">
    <br>  <p class="text-center">More bootstrap 4 components on <a href="http://bootstrap-ecommerce.com/"> Bootstrap-ecommerce.com</a></p>
    <hr>

    <div class="row">
        <aside class="col-sm-4">
            <p>Filter 1</p>


            <div class="card">
                <article class="card-group-item">
                    <header class="card-header">
                        <h6 class="title">Brands </h6>
                    </header>
                    <div class="filter-content">
                        <div class="card-body">
                            <form>
                                <label class="form-check">
                                    <input class="form-check-input" type="checkbox" value="">
                                    <span class="form-check-label">
				    Mersedes Benz
				  </span>
                                </label> <!-- form-check.// -->
                                <label class="form-check">
                                    <input class="form-check-input" type="checkbox" value="">
                                    <span class="form-check-label">
				    Nissan Altima
				  </span>
                                </label>  <!-- form-check.// -->
                                <label class="form-check">
                                    <input class="form-check-input" type="checkbox" value="">
                                    <span class="form-check-label">
				    Another Brand
				  </span>
                                </label>  <!-- form-check.// -->
                            </form>

                        </div> <!-- card-body.// -->
                    </div>
                </article> <!-- card-group-item.// -->

                <article class="card-group-item">
                    <header class="card-header">
                        <h6 class="title">Choose type </h6>
                    </header>
                    <div class="filter-content">
                        <div class="card-body">
                            <label class="form-check">
                                <input class="form-check-input" type="radio" name="exampleRadio" value="">
                                <span class="form-check-label">
			    First hand items
			  </span>
                            </label>
                            <label class="form-check">
                                <input class="form-check-input" type="radio" name="exampleRadio" value="">
                                <span class="form-check-label">
			    Brand new items
			  </span>
                            </label>
                            <label class="form-check">
                                <input class="form-check-input" type="radio" name="exampleRadio" value="">
                                <span class="form-check-label">
			    Some other option
			  </span>
                            </label>
                        </div> <!-- card-body.// -->
                    </div>
                </article> <!-- card-group-item.// -->
            </div> <!-- card.// -->




        </aside> <!-- col.// -->
        <aside class="col-sm-4">
            <p>Filter 2</p>


            <div class="card">
                <article class="card-group-item">
                    <header class="card-header"><h6 class="title">Similar category </h6></header>
                    <div class="filter-content">
                        <div class="list-group list-group-flush">
                            <a href="#" class="list-group-item">Cras justo odio <span class="float-right badge badge-light round">142</span> </a>
                            <a href="#" class="list-group-item">Dapibus ac facilisis  <span class="float-right badge badge-light round">3</span>  </a>
                            <a href="#" class="list-group-item">Morbi leo risus <span class="float-right badge badge-light round">32</span>  </a>
                            <a href="#" class="list-group-item">Another item <span class="float-right badge badge-light round">12</span>  </a>
                        </div>  <!-- list-group .// -->
                    </div>
                </article> <!-- card-group-item.// -->
                <article class="card-group-item">
                    <header class="card-header"><h6 class="title">Color check</h6></header>
                    <div class="filter-content">
                        <div class="card-body">
                            <label class="btn btn-danger">
                                <input class="" type="checkbox" name="myradio" value="">
                                <span class="form-check-label">Red</span>
                            </label>
                            <label class="btn btn-success">
                                <input class="" type="checkbox" name="myradio" value="">
                                <span class="form-check-label">Green</span>
                            </label>
                            <label class="btn btn-primary">
                                <input class="" type="checkbox" name="myradio" value="">
                                <span class="form-check-label">Blue</span>
                            </label>
                        </div> <!-- card-body.// -->
                    </div>
                </article> <!-- card-group-item.// -->
            </div> <!-- card.// -->



        </aside> <!-- col.// -->
        <aside class="col-sm-4">
            <p>Filter  3</p>



            <div class="card">
                <article class="card-group-item">
                    <header class="card-header">
                        <h6 class="title">Range input </h6>
                    </header>
                    <div class="filter-content">
                        <div class="card-body">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label>Min</label>
                                    <input type="number" class="form-control" id="inputEmail4" placeholder="$0">
                                </div>
                                <div class="form-group col-md-6 text-right">
                                    <label>Max</label>
                                    <input type="number" class="form-control" placeholder="$1,0000">
                                </div>
                            </div>
                        </div> <!-- card-body.// -->
                    </div>
                </article> <!-- card-group-item.// -->
                <article class="card-group-item">
                    <header class="card-header">
                        <h6 class="title">Selection </h6>
                    </header>
                    <div class="filter-content">
                        <div class="card-body">
                            <div class="custom-control custom-checkbox">
                                <span class="float-right badge badge-light round">52</span>
                                <input type="checkbox" class="custom-control-input" id="Check1">
                                <label class="custom-control-label" for="Check1">Samsung</label>
                            </div> <!-- form-check.// -->

                            <div class="custom-control custom-checkbox">
                                <span class="float-right badge badge-light round">132</span>
                                <input type="checkbox" class="custom-control-input" id="Check2">
                                <label class="custom-control-label" for="Check2">Black berry</label>
                            </div> <!-- form-check.// -->

                            <div class="custom-control custom-checkbox">
                                <span class="float-right badge badge-light round">17</span>
                                <input type="checkbox" class="custom-control-input" id="Check3">
                                <label class="custom-control-label" for="Check3">Samsung</label>
                            </div> <!-- form-check.// -->

                            <div class="custom-control custom-checkbox">
                                <span class="float-right badge badge-light round">7</span>
                                <input type="checkbox" class="custom-control-input" id="Check4">
                                <label class="custom-control-label" for="Check4">Other Brand</label>
                            </div> <!-- form-check.// -->
                        </div> <!-- card-body.// -->
                    </div>
                </article> <!-- card-group-item.// -->
            </div> <!-- card.// -->



        </aside> <!-- col.// -->
    </div> <!-- row.// -->

</div>
<%--
<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <h1 class="">
                <fmt:message key="filmList.title"/>
            </h1>
            <div class="film-posts py-4">
                <div class="row">
                    <jsp:useBean id="filmList" scope="request" type="java.util.List"/>
                    <c:forEach var="film" items="${filmList}" varStatus="counter">
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
                                        <li class="card-text"><fmt:message key="film.sessionAmount"/>: 3</li>
                                    </ul>
                                    <button type="button" class="btn btn-lg btn-block btn-primary">
                                        <fmt:message key="film.buyTicket"/>
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
</main>--%>

<jsp:include page="fragments/footer.jsp" />



