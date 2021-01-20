<%--
  Created by IntelliJ IDEA.
  User: Ulyana
  Date: 11.01.2021
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<footer class="footer text-white" id="contacts">
    <div class="container-fluid py-3">
        <div class="row">
            <div class="col-md-3">
                <h5><fmt:message key="footer.call.us"/></h5></div>
            <div class="col-md-3"></div>
            <div class="col-md-3"><i class="fa fa-phone"></i> +7 (707) 111-11-11</div>
            <div class="col-md-3"></div>
        </div>
        <div class="row">
            <div class="col-md-3"><fmt:message key="footer.text"/></div>
            <div class="col-md-3"></div>
            <div class="col-md-3"><i class="fa fa-instagram"></i> @pizza_delivery</div>
            <div class="col-md-3 text-right small align-self-end">©2020 Derkach Ulyana</div>
        </div>
    </div>
</footer>
