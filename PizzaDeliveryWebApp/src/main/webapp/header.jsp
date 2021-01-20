<%--
  Created by IntelliJ IDEA.
  User: Ulyana
  Date: 10.01.2021
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<section id="header">
    <nav class="navbar navbar-expand-lg fixed-top">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <ul class="navbar-nav ml-auto">
<c:choose>
    <c:when test="${sessionScope.user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="#login"><fmt:message key="button.login"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="register.jsp?lang=${sessionScope.lang}"><fmt:message key="button.signup"/></a>
                </li>
    </c:when>
    <c:when test="${sessionScope.user != null}">
        <li class="nav-item">
            <a class="nav-link" href="/logout"><fmt:message key="button.logout"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="personalCabinet.jsp?lang=${sessionScope.lang}"><fmt:message key="button.personal.cabinet"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/orders?lang=${sessionScope.lang}"><fmt:message key="button.orders"/></a>
        </li>
    </c:when>
</c:choose>
                <li class="nav-item">
                    <a class="nav-link" href="#contacts"><fmt:message key="button.contacts"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="?lang=en">EN</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="?lang=ru">RU</a>
                </li>
            </ul>
        </div>
    </nav>
</section>
<div class="jumbotron text-center">
    <div class="intro">
        <h1><fmt:message key="label.main"/></h1>
        <c:choose>
            <c:when test="${!sessionScope.user.admin}">
                <button type="button" class="btn btn-outline-light"><a href="/showMenu?lang=${sessionScope.lang}"><fmt:message key="button.menu"/></a> </button>
            </c:when>
            <c:otherwise>
                <button type="button" class="btn btn-outline-light"><a href="/showMenu?lang=${sessionScope.lang}"><fmt:message key="button.admin.menu"/></a></button>
            </c:otherwise>
        </c:choose>

    </div>
</div>
