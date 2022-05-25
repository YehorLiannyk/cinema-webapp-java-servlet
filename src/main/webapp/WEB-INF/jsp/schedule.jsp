<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 18.05.2022
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/menu.jsp"/>

<fmt:bundle basename="i18n">
    <fmt:message key="general.currency.short" var="currency"/>
    <fmt:message key="schedule.title" var="pageTitle"/>
    <fmt:message key="session.time" var="time"/>
    <fmt:message key="session.timePrefix" var="timePrefix"/>
    <fmt:message key="session.seatsRemain" var="seatsRemain"/>
    <fmt:message key="session.ticketPrice" var="ticketPrice"/>
    <fmt:message key="session.buyTicket" var="buyTicket"/>
    <fmt:message key="film.duration" var="duration"/>
    <fmt:message key="film.duration.postfix" var="durationPostfix"/>
    <fmt:message key="film.genres" var="genres"/>
    <fmt:message key="film.aboutFilm" var="aboutFilm"/>
</fmt:bundle>

<main class="container" data-new-gr-c-s-check-loaded="14.1062.0" data-gr-ext-installed="">

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
                            <a href="#" class="list-group-item">Cras justo odio <span
                                    class="float-right badge badge-light round">142</span> </a>
                            <a href="#" class="list-group-item">Dapibus ac facilisis <span
                                    class="float-right badge badge-light round">3</span> </a>
                            <a href="#" class="list-group-item">Morbi leo risus <span
                                    class="float-right badge badge-light round">32</span> </a>
                            <a href="#" class="list-group-item">Another item <span
                                    class="float-right badge badge-light round">12</span> </a>
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
            <p>Filter 3</p>


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

    <div class="container-fluid">
        <h1>${pageTitle}</h1>
        <div class="row">
            <div class="col-md-12">
                <div class="film-posts py-4">

                    <c:forEach var="session" items="${requestScope.sessionList}">
                        <c:set var="film" value="${session.getFilm()}"/>
                        <div class="p-4 film-post card w-100">
                            <div class="row card-body">
                                <div class="col-md-2">
                                    <img class="poster-img card-img" src="${film.posterUrl}">
                                </div>
                                <div class="col-md-7">
                                    <h2 class="card-title">${film.name}</h2>
                                    <ul class="list-unstyled mt-3 mb-4">
                                        <li class="card-text">${genres}:
                                            <c:forEach var="genre" items="${film.genreList}" varStatus="counter">
                                                <c:if test="${counter.index < (film.genreList.size() - 1)}">
                                                    <c:out value="${genre.name},"/>
                                                </c:if>
                                                <c:if test="${counter.index == (film.genreList.size() - 1)}">
                                                    <c:out value="${genre.name}"/>
                                                </c:if>
                                            </c:forEach>
                                        </li>
                                        <li class="card-text">
                                                ${duration}: ${film.getDurationInMinutes()} ${durationPostfix}
                                        </li>
                                        <li class="card-text">
                                                ${time}: ${session.date} ${timePrefix} ${session.time}
                                        </li>
                                        <li class="card-text">
                                                ${seatsRemain}: ###
                                        </li>
                                        <li class="card-text">
                                                ${ticketPrice}: ${session.ticketPrice} ${currency}
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-md-3">
                                    <button type="button" class="btn btn-lg btn-block btn-primary">
                                            ${aboutFilm}
                                    </button>
                                    <button type="button" class="btn btn-lg btn-block btn-primary">
                                            ${buyTicket}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <nav class="blog-pagination">
                    <a class="btn btn-outline-primary" href="#">Older</a>
                    <a class="btn btn-outline-secondary disabled" href="#">Newer</a>
                </nav>
            </div>
        </div>
    </div>

    <%--========================--%>
    <%--<div class="container-fluid">
        <h1>Bootstrap Table</h1>

        <p>A table with third party integration extension Filter control extension Data export pour exporter</p>


        <div class="bootstrap-table">
            <div class="fixed-table-toolbar">
                <div class="bars pull-left">
                    <div id="toolbar">
                        <select class="form-control">
                            <option value="">Export Basic</option>
                            <option value="all">Export All</option>
                            <option value="selected">Export Selected</option>
                        </select>
                    </div>
                </div>
                <div class="columns columns-right btn-group pull-right">
                    <div class="export btn-group">
                        <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" type="button"><i
                                class="glyphicon glyphicon-export icon-share"></i> <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li data-type="json"><a href="javascript:void(0)">JSON</a></li>
                            <li data-type="xml"><a href="javascript:void(0)">XML</a></li>
                            <li data-type="csv"><a href="javascript:void(0)">CSV</a></li>
                            <li data-type="txt"><a href="javascript:void(0)">TXT</a></li>
                            <li data-type="sql"><a href="javascript:void(0)">SQL</a></li>
                            <li data-type="excel"><a href="javascript:void(0)">MS-Excel</a></li>
                        </ul>
                    </div>
                </div>
                <div class="pull-right search"><input class="form-control" type="text" placeholder="Search"></div>
            </div>
            <div class="fixed-table-container" style="padding-bottom: 0px;">
                <div class="fixed-table-header" style="display: none;">
                    <table></table>
                </div>
                <div class="fixed-table-body">
                    <div class="fixed-table-loading" style="top: 42px;">Loading, please wait...</div>
                    <table id="table" data-toggle="table" data-search="true" data-filter-control="true"
                           data-show-export="true" data-click-to-select="true" data-toolbar="#toolbar"
                           class="table-responsive table table-hover">
                        <thead>
                        <tr>
                            <th class="bs-checkbox " style="width: 36px; " data-field="state" tabindex="0">
                                <div class="th-inner "><input name="btSelectAll" type="checkbox"></div>
                                <div class="fht-cell">
                                    <div style="height: 34px;"></div>
                                </div>
                            </th>
                            <th style="" data-field="prenom" tabindex="0">
                                <div class="th-inner sortable both">First Name</div>
                                <div class="fht-cell">
                                    <div style="margin: 0px 2px 2px 2px;" class="filterControl"><input type="text"
                                                                                                       class="form-control"
                                                                                                       style="width: 100%; visibility: visible">
                                    </div>
                                </div>
                            </th>
                            <th style="" data-field="date" tabindex="0">
                                <div class="th-inner sortable both">Date</div>
                                <div class="fht-cell">
                                    <div style="margin: 0px 2px 2px 2px;" class="filterControl"><select
                                            class="date form-control" style="width: 100%; visibility: visible">
                                        <option value=""></option>
                                        <option value="01/09/2015">01/09/2015</option>
                                        <option value="01/10/2015">01/10/2015</option>
                                        <option value="05/09/2015">05/09/2015</option>
                                        <option value="07/09/2015">07/09/2015</option>
                                    </select></div>
                                </div>
                            </th>
                            <th style="" data-field="examen" tabindex="0">
                                <div class="th-inner sortable both">Examination</div>
                                <div class="fht-cell">
                                    <div style="margin: 0px 2px 2px 2px;" class="filterControl"><select
                                            class="examen form-control" style="width: 100%; visibility: visible">
                                        <option value=""></option>
                                        <option value="Français">Français</option>
                                        <option value="Mathématiques">Mathématiques</option>
                                        <option value="Philosophie">Philosophie</option>
                                    </select></div>
                                </div>
                            </th>
                            <th style="" data-field="note" tabindex="0">
                                <div class="th-inner sortable both">Note</div>
                                <div class="fht-cell">
                                    <div style="height: 34px;"></div>
                                </div>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr data-index="0">
                            <td class="bs-checkbox "><input data-index="0" name="btSelectItem" type="checkbox"></td>
                            <td style="">Jitender</td>
                            <td style="">01/09/2015</td>
                            <td style="">Français</td>
                            <td style="">12/20</td>
                        </tr>
                        <tr data-index="1">
                            <td class="bs-checkbox "><input data-index="1" name="btSelectItem" type="checkbox"></td>
                            <td style="">Jahid</td>
                            <td style="">05/09/2015</td>
                            <td style="">Philosophie</td>
                            <td style="">8/20</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="fixed-table-footer" style="display: none;">
                    <table>
                        <tbody>
                        <tr></tr>
                        </tbody>
                    </table>
                </div>
                <div class="fixed-table-pagination" style="display: none;"></div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>--%>
</main>
<jsp:include page="fragments/footer.jsp"/>



