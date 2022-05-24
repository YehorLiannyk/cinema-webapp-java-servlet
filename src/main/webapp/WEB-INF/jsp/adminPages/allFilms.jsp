<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 24.05.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-12 blog-main">
            <h1 class="">
                Add a new film
            </h1>
            <nav class="blog-pagination">
                <a class="btn btn-outline-primary" href="#">Older</a>
                <a class="btn btn-outline-secondary disabled" href="#">Newer</a>
            </nav>
        </div>
    </div>
</main>
<jsp:include page="../fragments/footer.jsp"/>