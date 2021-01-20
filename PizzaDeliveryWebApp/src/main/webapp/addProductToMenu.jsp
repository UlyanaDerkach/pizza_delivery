<%--
  Created by IntelliJ IDEA.
  User: Ulyana
  Date: 18.01.2021
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/style.css">

</head>
<body>
<c:if test="${sessionScope.user != null}">

<jsp:include page="secondaryHeader.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header"><fmt:message key="button.add.product"/></div>
                <div class="card-body">

                    <form class="form-horizontal" action="/product/add" id="addProductForm" method="post" enctype="multipart/form-data">

                        <div class="form-group">
                            <label for="productName" class="cols-sm-2 control-label"><fmt:message key="label.product.name"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="productName" id="productName" placeholder="<fmt:message key="label.product.name"/>" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="cols-sm-2 control-label"><fmt:message key="label.product.desc"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="description" id="description" placeholder="<fmt:message key="label.product.desc"/> "/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productPrice" class="cols-sm-2 control-label"><fmt:message key="label.product.price"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="number" class="form-control" name="productPrice" id="productPrice" placeholder=<fmt:message key="label.product.price"/> />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="categoryId" class="cols-sm-2 control-label"><fmt:message key="label.product.category"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <select name="categoryId" id="categoryId">
                                        <option value="1"><fmt:message key="menu.pizza"/></option>
                                        <option value="2"><fmt:message key="menu.beverages"/></option>
                                        <option value="3"><fmt:message key="menu.desserts"/></option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="file" name="file" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <button type="submit" form="addProductForm" class="btn btn-primary btn-lg btn-block login-button"><fmt:message key="button.add.product"/></button>
                        </div>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

</c:if>
<c:if test="${sessionScope.user eq null}">
    <jsp:include page="accessError.jsp"/>
</c:if>


</body>
</html>
