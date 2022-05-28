<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 18.05.2022
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/menu.jsp"/>

<fmt:bundle basename="i18n">
    <fmt:message key="register.passwordMatch" var="passwordMatch"/>
    <fmt:message key="register.passwordNotMatch" var="passwordNotMatch"/>
</fmt:bundle>

<main class="container">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-12 col-xl-11">
            <div class="card text-black" style="border-radius: 25px;">
                <div class="card-body p-md-5">
                    <div class="row justify-content-center">
                        <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                            <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                            <form class="mx-1 mx-md-4" name="command" method="post" action="main">
                                <input type="hidden" name="command" value="register">

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-user fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="text" name="firstName" id="form3Example1c" class="form-control"
                                               required/>
                                        <label class="form-label" for="form3Example1c">Your first name</label>
                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-user fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="text" name="secondName" id="form3Example2c" class="form-control"
                                               required/>
                                        <label class="form-label" for="form3Example2c">Your second name</label>
                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-envelope fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="email" name="email" id="form3Example3c" class="form-control"
                                               required/>
                                        <label class="form-label" for="form3Example3c">Your Email</label>
                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-lock fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="password" name="password" id="password" class="form-control"
                                               onkeyup='check();' required/>
                                        <label class="form-label" for="password">Password</label>
                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-key fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="password" id="confirm_password" name="confirm_password"
                                               class="form-control"
                                               onkeyup='check();' required/>
                                        <label class="form-label" for="confirm_password">Repeat your password</label>
                                        <span id='message'></span>
                                    </div>
                                </div>

                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fa fa-user fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input type="tel" name="phoneNumber" placeholder="380671234567"
                                               pattern="380[0-9]{4}[0-9]{5}" maxlength="12" id="form3Example5c"
                                               class="form-control"/>
                                        <label class="form-label" for="form3Example5c">Your phone number</label>
                                    </div>
                                </div>

                                <div class="form-check d-flex mb-5">
                                    <input class="form-check-input me-2" name="notification" type="checkbox" checked
                                           id="form2Example3c"/>
                                    <label class="form-check-label" for="form2Example3c">
                                        I want to receive all notifications
                                    </label>
                                </div>

                                <div class="g-recaptcha"
                                     data-sitekey="6LeFvCUgAAAAACAtqpZg-ECgUPYLBP-_GqY8D0OT"></div>

                                <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                    <button type="submit" class="btn btn-primary btn-lg">Register</button>
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
</script>


<jsp:include page="fragments/footer.jsp"/>
