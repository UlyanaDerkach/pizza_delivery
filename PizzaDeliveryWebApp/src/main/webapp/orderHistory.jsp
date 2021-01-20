<%--
  Created by IntelliJ IDEA.
  User: Ulyana
  Date: 13.01.2021
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="imgPath" var="img"/>
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
<jsp:include page="secondaryHeader.jsp"/>
<div class="pb-5">
    <div class="container">
        <div class="row">
            <c:if test="${!sessionScope.user.admin}">
                <c:forEach var="order" items="${sessionScope.orders}">
                    <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                        <h4 id="orderId"><fmt:message  key="label.order.id"/>${order.id}</h4>
                        <fmt:parseDate  value="${order.orderDate}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
                        <fmt:parseDate  value="${order.orderTime}"  type="time" pattern="HH:mm" var="parsedTime" />
                        <div><fmt:message key="label.order.datetime"/><fmt:formatDate value="${parsedDate}" type="date" />, <fmt:formatDate value="${parsedTime}" type="time" timeStyle="short"/></div>
                        <c:forEach var="status" items="${sessionScope.statuses}">
                            <c:if test="${order.statusId eq status.id}">
                                    <div><fmt:message key="label.current.status"/>${status.name}</div>
                                </c:if>
                        </c:forEach>
                        <!-- Shopping cart table -->
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col" class="border-0 bg-light">
                                        <div class="p-2 px-3 text-uppercase"><fmt:message key="label.product"/></div>
                                    <th scope="col" class="border-0 bg-light">
                                        <div class="py-2 text-uppercase"><fmt:message key="label.quantity"/></div>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="orderItem" items="${sessionScope.orderItems}">
                                    <c:if test="${order.id eq orderItem.orderId}">
                                        <tr>
                                            <th scope="row" class="border-0">
                                                <div class="p-2">
                                                    <img src="<fmt:message key="img.path.base" bundle="${img}"/>${orderItem.picturePath}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                    <div class="ml-3 d-inline-block align-middle">
                                                        <h5 class="mb-0 text-dark d-inline-block align-middle">${orderItem.productName}</h5>
                                                    </div>
                                                </div>
                                            </th>
                                            <td class="border-0 align-middle"><strong>${orderItem.productAmount}</strong></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <th scope="row" class="border-0">
                                        <div class="p-2">
                                            <div class="ml-3 d-inline-block align-middle">
                                                <h5 class="mb-0 text-dark d-inline-block align-middle"><strong><fmt:message key="label.total.sum"/></strong></h5>
                                            </div>
                                        </div>
                                    </th>
                                    <td class="border-0 align-middle" id="overall${order.id}"><strong>${order.totalSum}</strong></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:forEach>
        </div>
    </div>
</div>
            </c:if>
<c:if test="${sessionScope.user.admin}">
    <c:forEach var="order" items="${sessionScope.orders}">
        <div class="col-lg-12 p-5 bg-white rounded shadow-lg mb-5">
            <form method="post" action="/order/status/change" id="orderForm">
            <h4><fmt:message key="label.order.id"/>${order.id}</h4>
            <input type="hidden" value="${order.id}" name="orderId">
                <fmt:parseDate  value="${order.orderDate}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
                <fmt:parseDate  value="${order.orderTime}"  type="time" pattern="HH:mm" var="parsedTime" />
                <div><fmt:message key="label.order.datetime"/><fmt:formatDate value="${parsedDate}" type="date" />, <fmt:formatDate value="${parsedTime}" type="time" timeStyle="short"/></div>
                <fmt:parseDate  value="${order.deliveryDate}"  type="date" pattern="yyyy-MM-dd" var="parsedDeliveryDate" />
                <fmt:parseDate  value="${order.deliveryTime}"  type="time" pattern="HH:mm" var="parsedDeliveryTime" />
                <div><fmt:message key="label.delivery.datetime"/><fmt:formatDate value="${parsedDeliveryDate}" type="date" />, <fmt:formatDate value="${parsedDeliveryTime}" type="time" timeStyle="short"/></div>

                <c:forEach var="customer" items="${sessionScope.customers}">
                    <c:if test="${order.userId eq customer.id}">
                        <div><fmt:message key="label.client.name"/>${customer.firstName} ${customer.lastName}</div>
                        <div>${customer.phone}</div>
                        <div>${customer.address}</div>
                    </c:if>
                </c:forEach>

<%--                    staus here--%>


            <!-- Shopping cart table -->
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col" class="border-0 bg-light">
                            <div class="p-2 px-3 text-uppercase"><fmt:message key="label.product"/></div>
                        <th scope="col" class="border-0 bg-light">
                            <div class="py-2 text-uppercase"><fmt:message key="label.quantity"/></div>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="orderItem" items="${sessionScope.orderItems}">
                        <c:if test="${order.id eq orderItem.orderId}">
                            <tr>
                                <th scope="row" class="border-0">
                                    <div class="p-2">
                                        <img src="<fmt:message key="img.path.base" bundle="${img}"/>${orderItem.picturePath}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                        <div class="ml-3 d-inline-block align-middle">
                                            <h5 class="mb-0 text-dark d-inline-block align-middle">${orderItem.productName}</h5>
                                        </div>
                                    </div>
                                </th>
                                <td class="border-0 align-middle"><strong>${orderItem.productAmount}</strong></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <tr>
                        <th scope="row" class="border-0">
                            <div class="p-2">
                                <div class="ml-3 d-inline-block align-middle">
                                    <h5 class="mb-0 text-dark d-inline-block align-middle"><strong><fmt:message key="label.total.sum"/></strong></h5>
                                </div>
                            </div>
                        </th>
                        <td class="border-0 align-middle" id="overall${order.id}"><strong>${order.totalSum}</strong></td>
                    </tr>
                    </tbody>
                </table>
            </div>
                <div class="form-group">
                <select name="statusId" form="orderForm">
                    <c:forEach var="status" items="${sessionScope.statuses}">
                        <c:choose>
                            <c:when test="${order.statusId eq status.id}">
                                <option value="${status.id}" class="bg-dark">${status.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${status.id}">${status.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            <button type="submit" form="orderForm" class="btn btn-dark mt-3"><fmt:message key="update.status"/></button>
                </div>
            </form>
        </div>
    </c:forEach>
    </div>
    </div>
    </div>
</c:if>




<script type="text/javascript" src="resources/js/script.js"></script>
</body>
</html>
