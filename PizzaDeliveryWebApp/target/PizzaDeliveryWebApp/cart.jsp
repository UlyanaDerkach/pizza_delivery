<%--
  Created by IntelliJ IDEA.
  User: Ulyana
  Date: 10.01.2021
  Time: 11:47
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
<c:choose>
    <c:when test="${sessionScope.cart.size() == 0 || sessionScope.cart == null}">
        <p><fmt:message key="label.cart.empty"/></p>
    </c:when>
    <c:otherwise>
        <form id="order" action="/order" method="post">
<div class="pb-5">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                <!-- Shopping cart table -->
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col" class="border-0 bg-light">
                                <div class="p-2 px-3 text-uppercase"><fmt:message key="label.product"/></div>
                            </th>
                            <th scope="col" class="border-0 bg-light">
                                <div class="py-2 text-uppercase"><fmt:message key="label.product.price"/></div>
                            </th>
                            <th scope="col" class="border-0 bg-light">
                                <div class="py-2 text-uppercase"><fmt:message key="label.quantity"/></div>
                            </th>
                            <th scope="col" class="border-0 bg-light">
                                <div class="py-2 text-uppercase"><fmt:message key="button.delete.product"/></div>
                            </th>
                        </tr>
                        </thead>
                        <tbody>

        <c:forEach var="product" items="${sessionScope.cart}">
            <tr class="cart-row">
                <th scope="row" class="border-0">
                    <div class="p-2">
                        <div class="ml-3 d-inline-block align-middle">
                            <h5 class="mb-0 text-dark d-inline-block align-middle">${product.name}</h5>
                        </div>
                    </div>
                </th>
                <td class="border-0 align-middle"><strong>${product.price}</strong></td>
                <td class="border-0 align-middle"><strong><input class="cart-variant--quantity_input" type="number" id="product${product.id}Amount" name="product${product.id}Amount" value="0" data-unit-price="${product.price}" min="1" max="10" ></strong></td>
                <td class="border-0 align-middle"><a href="/removeFromCart?productId=${product.id}" class="text-dark"><i class="fa fa-trash"></i></a></td>
            </tr>

        </c:forEach>
        <tr>
            <th scope="row" class="border-0">
                <div class="p-2">
                    <div class="ml-3 d-inline-block align-middle">
                        <h5 class="mb-0 text-dark d-inline-block align-middle"><strong><fmt:message key="label.total.sum"/></strong></h5>
                    </div>
                </div>
            </th>
            <td class="border-0 align-middle"></td>
            <td class="border-0 align-middle"></td>
            <td class="border-0 align-middle"><strong><input type="text" id="totalSum" name="totalSum" readonly style="border: none; outline: none;"></strong></td>
        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
            <label for="deliveryDate"><fmt:message key="label.delivery.datetime"/></label>
            <input type="date" name="deliveryDate" id="deliveryDate">
            <input type="time" name="deliveryTime" id="deliveryTime">

        <button type="submit" form="order" class="btn btn-dark"><fmt:message key="button.make.order"/></button>
        </form>
    </c:otherwise>
</c:choose>
<script type="text/javascript" src="resources/js/script.js"></script>
</c:if>
<c:if test="${sessionScope.user eq null}">
    <jsp:include page="accessError.jsp"/>
</c:if>

</body>
</html>
