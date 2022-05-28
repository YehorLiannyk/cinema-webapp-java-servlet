<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="userRole" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n" prefix="menu.">
    <fmt:message key="title" var="title"/>
    <fmt:message key="main" var="main"/>
    <fmt:message key="guest.login" var="login"/>
    <fmt:message key="guest.register" var="register"/>
    <fmt:message key="schedule" var="schedule"/>
    <fmt:message key="user.logout" var="logout"/>
    <fmt:message key="user.myProfile" var="myProfile"/>
</fmt:bundle>

<c:url value="/main" var="main_url"/>
<c:url value="/main?command=schedulePage" var="schedule_url"/>
<c:url value="/main?command=loginPage" var="login_url"/>
<c:url value="/main?command=registerPage" var="register_url"/>
<c:url value="/main?command=logout" var="logout_url"/>
<c:url value="/main?command=profilePage" var="profile_url"/>
<c:url value="/main?command=addFilmPage" var="addFilm_url"/>
<c:url value="/main?command=filmsSettingPage" var="filmSetting_url"/>
<c:url value="/main?command=addSessionPage" var="addSession_url"/>
<c:url value="/main?command=sessionsSettingPage" var="sessionSetting_url"/>


<nav class="container-fluid site-header sticky-top">
    <div class="container d-flex flex-column flex-md-row justify-content-between py-2">
        <span class="py-2 menu-item">${title}</span>

        <a class="py-2 d-none d-md-inline-block" href="${main_url}">${main}</a>

        <a class="py-2 d-none d-md-inline-block" href="${schedule_url}">${schedule}</a>

        <c:if test="${userRole == null || userRole == 'GUEST' || userRole.equals('')}">
            <a class="py-2 d-none d-md-inline-block" href="${login_url}">
                    ${login}
            </a>

            <a class="py-2 d-none d-md-inline-block" href="${register_url}">${register}</a>
        </c:if>

        <c:if test="${userRole == 'USER'}">
            <a class="py-2 d-none d-md-inline-block" href="${logout_url}">
                    ${logout}
            </a>
            <a class="py-2 d-none d-md-inline-block" href="${profile_url}">
                    ${myProfile}
            </a>
        </c:if>

        <c:if test="${userRole == 'ADMIN'}">

            <div class="dropdown">
                <button class="py-2 d-none d-md-inline-block dropdown-toggle " type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Film/Session
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <ul style="list-style-type: none;" class="px-3">
                        <li class="menu-li py-1"><a href="${addFilm_url}">Add film</a></li>
                        <li class="menu-li py-1"><a href="${filmSetting_url}">Films
                            setting</a></li>
                        <li class="menu-li py-1"><a href="${addSession_url}">Add session</a>
                        </li>
                        <li class="menu-li py-1"><a href="${sessionSetting_url}">Sessions
                            setting</a></li>
                    </ul>
                </div>
            </div>
            <a class="py-2 d-none d-md-inline-block" href="${logout_url}">
                    ${logout}
            </a>
        </c:if>
    </div>
</nav>