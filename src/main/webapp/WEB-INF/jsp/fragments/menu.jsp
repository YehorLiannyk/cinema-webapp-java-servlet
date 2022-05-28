<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 21.05.2022
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="i18n"/>

<nav class="container-fluid site-header sticky-top">
    <div class="container d-flex flex-column flex-md-row justify-content-between py-2">
        <span class="py-2 menu-item"><fmt:message key="menu.title"/></span>
        <a class="py-2 d-none d-md-inline-block" href="<c:url value="/main"/>">
            <fmt:message key="menu.main"/>
        </a>

        <a class="py-2 d-none d-md-inline-block" href="<c:url value="/main?command=schedulePage"/>">
            <fmt:message key="menu.schedule"/>
        </a>

        <c:if test="${sessionScope.userRole == null || sessionScope.userRole == 'GUEST'}">
            <a class="py-2 d-none d-md-inline-block" href="<c:url value="/main?command=loginPage"/>">
                <fmt:message key="menu.guest.login"/>
            </a>

            <a class="py-2 d-none d-md-inline-block" href="<c:url value="/main?command=registerPage"/>">
                <fmt:message key="menu.guest.register"/>
            </a>
        </c:if>

        <c:if test="${sessionScope.userRole == 'USER'}">
            <a class="py-2 d-none d-md-inline-block" href="<c:url value="/main?command=logout"/>">
                <fmt:message key="menu.user.logout"/>
            </a>
            <a class="py-2 d-none d-md-inline-block" href="<c:url value="/main?command=profilePage"/>">
                <fmt:message key="menu.user.myProfile"/>
            </a>
        </c:if>

        <c:if test="${sessionScope.userRole == 'ADMIN'}">

            <div class="dropdown">
                <button class="py-2 d-none d-md-inline-block dropdown-toggle " type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Film/Session
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <ul style="list-style-type: none;" class="px-3">
                        <li class="menu-li py-1"><a href="<c:url value="/main?command=addFilmPage"/>">Add film</a></li>
                        <li class="menu-li py-1"><a href="<c:url value="/main?command=filmsSettingPage"/>">Films setting</a></li>
                        <li class="menu-li py-1"><a href="<c:url value="/main?command=addSessionPage"/>">Add session</a></li>
                        <li class="menu-li py-1"><a href="<c:url value="/main?command=sessionsSettingPage"/>">Sessions setting</a></li>
                    </ul>
                </div>
            </div>

            <a class="py-2 d-none d-md-inline-block" href="<c:url value="/main?command=logout"/>">
                <fmt:message key="menu.user.logout"/>
            </a>
        </c:if>
    </div>
</nav>
