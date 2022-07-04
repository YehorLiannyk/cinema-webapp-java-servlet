<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 18.05.2022
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<fmt:bundle basename="i18n">
    <fmt:message key="login.text" var="text"/>
    <fmt:message key="login.pageTitle" var="pageTitle"/>
    <fmt:message key="form.email" var="email"/>
    <fmt:message key="form.password" var="password"/>
    <fmt:message key="form.rememberMe" var="rememberMe"/>
    <fmt:message key="form.signin" var="signIn"/>
    <fmt:message key="form.requiredField" var="requiredField"/>
    <fmt:message key="valid.user.email.empty" var="emailEmpty"/>
    <fmt:message key="valid.user.email.length" var="emailLength"/>
    <fmt:message key="valid.user.email.invalid" var="emailInvalid"/>
</fmt:bundle>


<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<main class="container" data-new-gr-c-s-check-loaded="14.1062.0" data-gr-ext-installed="">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-12 col-xl-11">
            <div class="card text-black" style="border-radius: 25px;">
                <div class="card-body p-md-5">
                    <div class="row justify-content-center">
                        <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                            <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">${text}</p>

                            <form class="mx-1 mx-md-4" name="command" method="post" action="main">
                                <input type="hidden" name="command" value="login">

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-envelope fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="email" name="login" id="form3Example3c" class="form-control"
                                               placeholder="${requiredField}"
                                                <c:if test="${pageContext.request.getParameter('email') != null}">
                                                    value="${pageContext.request.getParameter('email')}"
                                                </c:if>
                                               required/>
                                        <label class="form-label" for="form3Example3c">${email}</label>

                                        <c:if test="${requestScope.email_length}">
                                            <p class="form-error">${emailLength}</p>
                                        </c:if>
                                        <c:if test="${requestScope.email_empty}">
                                            <p class="form-error">${emailEmpty}</p>
                                        </c:if>
                                        <c:if test="${requestScope.email_invalid}">
                                            <p class="form-error">${emailInvalid}</p>
                                        </c:if>

                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-lock fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="password" name="password" id="form3Example4c" class="form-control"
                                               placeholder="${requiredField}"
                                               value="${requestScope.password}" required/>
                                        <label class="form-label" for="form3Example4c">${password}</label>
                                    </div>
                                </div>

                                <div class="form-check d-flex mb-5">
                                    <input class="form-check-input me-2" name="rememberMe" value="rememberMe"
                                           type="checkbox" checked
                                           id="form2Example3c"/>
                                    <label class="form-check-label" for="form2Example3c">
                                        ${rememberMe}
                                    </label>
                                </div>

                                <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                    <button type="submit" class="btn btn-primary btn-lg">${signIn}</button>
                                </div>

                            </form>

                        </div>
                        <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                 class="img-fluid" alt="Sample image">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<ftg:footer/>