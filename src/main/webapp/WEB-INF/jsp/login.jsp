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
                                        <input type="hidden" name="command" value="login">

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input type="email" name="login" id="form3Example3c" class="form-control"  placeholder="(<fmt:message key="form.requiredField"/>)" value="${requestScope.email}" required/>
                                                <label class="form-label" for="form3Example3c"><fmt:message key="login.email"/></label>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input type="password" name="password" id="form3Example4c" class="form-control" placeholder="(<fmt:message key="form.requiredField"/>)" value="${requestScope.password}" required/>
                                                <label class="form-label" for="form3Example4c"><fmt:message key="login.password"/></label>
                                            </div>
                                        </div>

                                        <div class="form-check d-flex justify-content-center mb-5">
                                            <input class="form-check-input me-2" name="notification" type="checkbox" checked
                                                   id="form2Example3c"/>
                                            <label class="form-check-label" for="form2Example3c">
                                                <fmt:message key="login.rememberMe" />
                                            </label>
                                        </div>

                                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                            <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="login.signin"/></button>
                                        </div>

                                    </form>

                                </div>
                                <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                    <img src="https://www.drupal.org/files/project-images/reg_confirm_email_with_button_0.png"
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