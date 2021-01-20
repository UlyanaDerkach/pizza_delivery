<%--
  Created by IntelliJ IDEA.
  User: Ulyana
  Date: 06.01.2021
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="imgPath" var="img"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<jsp:include page="secondaryHeader.jsp"/>

<div>
    <c:forEach var="category" items="${sessionScope.categories}">
        <c:choose>
            <c:when test="${category.name == 'Pizza'}">
                <h2><fmt:message key="menu.pizza"/></h2>
            </c:when>
            <c:when test="${category.name == 'Beverages'}">
                <h2><fmt:message key="menu.beverages"/></h2>
            </c:when>
            <c:when test="${category.name == 'Desserts'}">
                <h2><fmt:message key="menu.desserts"/></h2>
            </c:when>
        </c:choose>
        <div class="container">
        <div class="row">
        <c:forEach var="product" items="${sessionScope.products}">
            <c:if test="${product.categoryId eq category.id}">
                <div class="col-sm-4 d-flex pb-3">
                            <div class="card">
                                <img class="card-img-top img-fluid" src="<fmt:message key="img.path.base" bundle="${img}"/>${product.picturePath}" alt="Restart server and I will appear" style="width: 100%; height: 15vw; object-fit: cover" >
                                <div class="card-body">
                                    <h4 class="card-title">${product.name}</h4>
                                    <p class="card-text">${product.description}</p>
                                    <div class="buy d-flex justify-content-between align-items-center">
                                        <div class="price text-success"><h5 class="mt-4">${product.price}</h5></div>
                                        <c:if test="${!sessionScope.user.admin and sessionScope.user != null}">
                                            <c:choose>
                                                <c:when test="${sessionScope.cart.contains(product)}">
                                                    <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="label.cart.in"/></h6>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="/addToCart?productId=${product.id}" class="btn btn-danger mt-3"><i class="fa fa-shopping-cart"></i><fmt:message key="button.add.to.cart"/></a>

                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </div>
                                    <c:if test="${sessionScope.user.admin}">
                                        <div class="text-right">
                                            <button type="button" class="btn btn-dark mt-3"><a href="/deleteProduct?productId=${product.id}" class="text-light"><i class="fa fa-trash"></i><fmt:message key="button.delete.product"/></a></button>
                                        </div>
                                    </c:if>

                                </div>
                            </div>
                        </div>
            </c:if>
        </c:forEach>
        </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
