<%--
  Created by IntelliJ IDEA.
  User: egorf
  Date: 24.05.2022
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>
<div class="d-flex justify-content-center align-items-center" id="main" style="background-color: wheat;">
    <h1 class="mr-3 pr-3 align-top border-right inline-block align-content-center">404</h1>
    <div class="inline-block align-middle">
    <h2 class="font-weight-normal lead" id="desc">Couldn't find this page</h2>
        <h2 class="font-weight-normal lead">Request from ${pageContext.errorData.requestURI} is failed</h2>
        <a href="${pageContext.request.getHeader("referer")}" class="btn btn-link">Back to previous page</a>
        <a href="main" class="btn btn-link">Back to Home</a>
    </div>
</div>
