<%--
  Created by IntelliJ IDEA.
  User: Ulyana
  Date: 11.01.2021
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<div id="login">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="/login" method="post">
                        <h3 class="text-center login-text"><fmt:message key="button.login"/></h3>
                        <c:if test="${sessionScope.errorLogin != null}">
                        <div class="text-danger"><fmt:message key="error.login"/></div>
                        </c:if>
                        <div class="form-group">
                            <label for="username" class="login-text"><fmt:message key="label.login"/></label><br>
                            <input type="text" name="login" id="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password" class="login-text"><fmt:message key="label.password"/></label><br>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <div class="text-center mx-auto">
                            <button type="submit" name="submit" class="btn btn-dark"><fmt:message key="button.login"/></button>
                        </div>

                </form>
            </form>
        </div>
            </div>
        </div>
    </div>
</div>
