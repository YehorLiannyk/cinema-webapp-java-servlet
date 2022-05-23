<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 18.05.2022
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/menu.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>

<main class="container text-center" data-new-gr-c-s-check-loaded="14.1062.0" data-gr-ext-installed="">
    <form class="form-signin" action="main" method="post">
        <input type="hidden" name="command" value="login">
        <img class="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72"
             height="72">
        <h1 class="h3 mb-3 font-weight-normal"></h1>
        <label for="inputEmail" class="sr-only"><fmt:message key="login.email"/></label>
        <input type="email" id="inputEmail" name="login" class="form-control"
               placeholder="<fmt:message key="login.email"/> (<fmt:message key="form.requiredField"/>)" required
               autofocus="">
        <label for="inputPassword" class="sr-only"><fmt:message key="login.password"/></label>
        <input type="password" id="inputPassword" name="password" class="form-control"
               placeholder="<fmt:message key="login.password"/> (<fmt:message key="form.requiredField"/>)" required>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> <fmt:message key="login.rememberMe"/>
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.signin"/></button>
    </form>

    <section class="vh-100">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-lg-12 col-xl-11">
                    <div class="card text-black" style="border-radius: 25px;">
                        <div class="card-body p-md-5">
                            <div class="row justify-content-center">
                                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                    <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"><fmt:message key="login.text"/></p>

                                    <form class="mx-1 mx-md-4" name="command" method="post" action="main">
                                        <input type="hidden" name="command" value="register">

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input type="text" name="firstName" id="form3Example1c" class="form-control" value="${requestScope['firstName']}" required/>
                                                <label class="form-label" for="form3Example1c">Your first name</label>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input type="text" name="secondName" id="form3Example2c" class="form-control"  value="${requestScope.secondName}" required/>
                                                <label class="form-label" for="form3Example2c">Your second name</label>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input type="email" name="email" id="form3Example3c" class="form-control"  value="${requestScope.email}" required/>
                                                <label class="form-label" for="form3Example3c">Your Email</label>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input type="password" name="password" id="form3Example4c" class="form-control" value="${requestScope.password}" required/>
                                                <label class="form-label" for="form3Example4c">Password</label>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input type="password" id="form3Example4cd" class="form-control" required/>
                                                <label class="form-label" for="form3Example4cd">Repeat your password</label>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input type="number" name="phoneNumber" id="form3Example5c" class="form-control" value="${requestScope.phoneNumber}"/>
                                                <label class="form-label" for="form3Example5c">Your phone number</label>
                                            </div>
                                        </div>

                                        <div class="form-check d-flex justify-content-center mb-5">
                                            <input class="form-check-input me-2" name="notification" type="checkbox" checked
                                                   id="form2Example3c"/>
                                            <label class="form-check-label" for="form2Example3c">
                                                I want to receive all notifications
                                            </label>
                                        </div>

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
        </div>
    </section>
</main>
<jsp:include page="fragments/footer.jsp"/>