<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: dimgrey">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa fa-bars"></i>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/index.jsp?lang=${sessionScope.lang}"><fmt:message key="button.home"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/showMenu?lang=${sessionScope.lang}"><fmt:message key="button.menu"/></a>
            </li>
<c:if test="${sessionScope.user != null}">
            <li class="nav-item">
                <a class="nav-link" href="/orders?lang=${sessionScope.lang}"><fmt:message key="button.orders"/></a>
            </li>
</c:if>
            <c:if test="${!sessionScope.user.admin and sessionScope.user != null}">
            <li class="nav-item">
                <a class="nav-link" href="/cart?lang=${sessionScope.lang}"><fmt:message key="button.cart"/></a>
            </li>
            </c:if>
            <c:if test="${sessionScope.user.admin}">
                <li class="nav-item">
                    <a class="nav-link" href="/addProductToMenu.jsp?lang=${sessionScope.lang}"><fmt:message key="button.add.product"/></a>
                </li>
            </c:if>
            <li class="nav-item">
            <a href="?lang=en" class="nav-link">EN</a></li>
            <li class="nav-item"><a href="?lang=ru" class="nav-link">RU</a></li>
        </ul>


    </div>
</nav>


