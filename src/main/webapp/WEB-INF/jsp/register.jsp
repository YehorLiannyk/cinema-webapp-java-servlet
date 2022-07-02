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
    <fmt:message key="register.pageTitle" var="pageTitle"/>
    <fmt:message key="form.passwordMatch" var="passwordMatch"/>
    <fmt:message key="form.passwordNotMatch" var="passwordNotMatch"/>
    <fmt:message key="form.email" var="email"/>
    <fmt:message key="form.password" var="password"/>
    <fmt:message key="form.fName" var="fName"/>
    <fmt:message key="form.sName" var="sName"/>
    <fmt:message key="form.signup" var="signup"/>
    <fmt:message key="form.signupBtn" var="signupBtn"/>
    <fmt:message key="form.notification" var="notification"/>
    <fmt:message key="form.phoneNumber" var="phoneNumber"/>
    <fmt:message key="form.passwordRepeat" var="passwordRepeat"/>
    <fmt:message key="form.requiredField" var="requiredField"/>
    <fmt:message key="form.nonRequiredField" var="nonRequiredField"/>
</fmt:bundle>

<fmt:bundle basename="i18n" prefix="valid.">
    <fmt:message key="onlyLetters" var="onlyLetters"/>
    <fmt:message key="onlyDigits" var="onlyDigits"/>
    <fmt:message key="user.fName.length" var="fNameLength"/>
    <fmt:message key="user.fName.empty" var="fNameEmpty"/>
    <fmt:message key="user.lName.length" var="lNameLength"/>
    <fmt:message key="user.lName.empty" var="lNameEmpty"/>
    <fmt:message key="user.email.empty" var="emailEmpty"/>
    <fmt:message key="user.email.length" var="emailLength"/>
    <fmt:message key="user.email.invalid" var="emailInvalid"/>
    <fmt:message key="user.password.empty" var="passwordEmpty"/>
    <fmt:message key="user.password.length" var="passwordLength"/>
    <fmt:message key="user.password.confirm.empty" var="passwordConfirmEmpty"/>
    <fmt:message key="user.password.confirm.notEqual" var="passwordConfirmNotEqual"/>
    <fmt:message key="user.phoneNumber.invalid" var="phoneNumberInvalid"/>
</fmt:bundle>

<ftg:header pageTitle="${pageTitle}"/>
<ftg:menu userRole="${sessionScope.userRole}"/>

<main class="container">
    <div class="row d-flex justify-content-center align-items-center">
        <div class="col-lg-12 col-xl-11">
            <div class="card text-black" style="border-radius: 25px;">
                <div class="card-body p-md-5">
                    <div class="row justify-content-center">
                        <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                            <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">${signup}</p>

                            <form class="mx-1 mx-md-4" name="command" method="post" action="main">
                                <input type="hidden" name="command" value="register">
                                <input type="hidden" name="rememberMe" value="rememberMe" checked/>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-user fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="text" name="firstName" id="firstName" class="form-control"
                                               placeholder="${requiredField}"
                                                <c:if test="${pageContext.request.getParameter('firstName') != null}">
                                                    value="${pageContext.request.getParameter('firstName')}"
                                                </c:if>
                                               required/>
                                        <label class="form-label" for="firstName">${fName}</label>

                                        <c:if test="${requestScope.fName_length}">
                                            <p class="form-error">${fNameLength}</p>
                                        </c:if>
                                        <c:if test="${requestScope.fName_empty}">
                                            <p class="form-error">${fNameEmpty}</p>
                                        </c:if>
                                        <c:if test="${requestScope.fName_invalid}">
                                            <p class="form-error">${onlyLetters}</p>
                                        </c:if>

                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-user fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="text" name="lastName" id="lastName" class="form-control"
                                               placeholder="${requiredField}"
                                                <c:if test="${pageContext.request.getParameter('lastName') != null}">
                                                    value="${pageContext.request.getParameter('lastName')}"
                                                </c:if>
                                               required/>
                                        <label class="form-label" for="lastName">${sName}</label>

                                        <c:if test="${requestScope.lName_length}">
                                            <p class="form-error">${lNameLength}</p>
                                        </c:if>
                                        <c:if test="${requestScope.lName_empty}">
                                            <p class="form-error">${lNameEmpty}</p>
                                        </c:if>
                                        <c:if test="${requestScope.lName_invalid}">
                                            <p class="form-error">${onlyLetters}</p>
                                        </c:if>

                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-envelope fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="email" name="email" id="email" class="form-control"
                                                placeholder="${requiredField}"
                                                <c:if test="${pageContext.request.getParameter('email') != null}">
                                                    value="${pageContext.request.getParameter('email')}"
                                                </c:if>
                                               required/>
                                        <label class="form-label" for="email">${email}</label>

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
                                        <input type="password" name="password" id="password" class="form-control"
                                               onkeyup='check();' placeholder="${requiredField}" required/>
                                        <label class="form-label" for="password">${password}</label>

                                        <c:if test="${requestScope.pass_empty}">
                                            <p class="form-error">${passwordEmpty}</p>
                                        </c:if>
                                        <c:if test="${requestScope.pass_length}">
                                            <p class="form-error">${passwordLength}</p>
                                        </c:if>

                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-key fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="password" id="confirm_password" name="confirm_password"
                                               class="form-control" placeholder="${requiredField}"
                                               onkeyup='check();' required/>
                                        <label class="form-label" for="confirm_password">${passwordRepeat}</label>
                                        <span id='message'></span>

                                        <c:if test="${requestScope.passConfirm_empty}">
                                            <p class="form-error">${passwordConfirmEmpty}</p>
                                        </c:if>
                                        <c:if test="${requestScope.passConfirm_notEqual}">
                                            <p class="form-error">${passwordConfirmNotEqual}</p>
                                        </c:if>

                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-user fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="text" name="phoneNumber"
                                               placeholder="380671234567 ${nonRequiredField}"
                                               id="phoneNumber" class="form-control"
                                                <c:if test="${pageContext.request.getParameter('phoneNumber') != null}">
                                                    value="${pageContext.request.getParameter('phoneNumber')}"
                                                </c:if>/>
                                        <label class="form-label" for="phoneNumber">${phoneNumber}</label>
                                        <c:if test="${requestScope.phone_invalid}">
                                            <p class="form-error">${phoneNumberInvalid}</p>
                                        </c:if>
                                    </div>
                                </div>

                                <div class="form-check d-flex mb-4">
                                    <input class="form-check-input me-2" name="notification" type="checkbox" checked
                                           id="notification"/>
                                    <label class="form-check-label" for="notification">
                                        ${notification}
                                    </label>
                                </div>

                                <div class="d-flex mb-5 g-recaptcha"
                                     data-sitekey="6LeFvCUgAAAAACAtqpZg-ECgUPYLBP-_GqY8D0OT">
                                </div>

                                <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                    <button type="submit" class="btn btn-primary btn-lg">${signupBtn}</button>
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

<script>
    $('#password, #confirm_password').on('keyup', function () {
        if ($('#password').val() == $('#confirm_password').val()) {
            $('#message').html('${passwordMatch}').css('color', 'green');
        } else
            $('#message').html('${passwordNotMatch}').css('color', 'red');
    });

    $("form").submit(function () {
        if ($('#password').val() != $('#confirm_password').val()) {
            return false;
        }
        return true;
    });

</script>

<ftg:footer/>
