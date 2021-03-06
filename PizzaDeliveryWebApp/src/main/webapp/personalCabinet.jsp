<%--
  Created by IntelliJ IDEA.
  User: Ulyana
  Date: 12.01.2021
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.lang}">
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
<c:choose>
    <c:when test="${param.isEdit || param.isEdit == null}">
<div class="col-md-8">
    <div class="card mb-3">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.fullName"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                        ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.email"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                        ${sessionScope.user.email}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.phone"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                        ${sessionScope.user.phone}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.birthDate"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <fmt:formatDate value="${sessionScope.user.birthDate}" type="date" />
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.address"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                        ${sessionScope.user.address}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0"><fmt:message key="label.login"/></h6>
                </div>
                <div class="col-sm-9 text-secondary">
                        ${sessionScope.user.login}
                </div>
            </div>
        </div>
    </div>
    </div>
    <button class="btn btn-dark"><a href="?isEdit=1" class="text-light"><fmt:message key="button.edit"/></a></button>
    </c:when>
    <c:otherwise>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="button.edit"/></div>
                    <div class="card-body">

                        <form class="form-horizontal" method="post" action="/userInfo/edit" id="editUserInfo">

                            <div class="form-group">
                                <label for="firstName" class="cols-sm-2 control-label"><fmt:message key="label.firstName"/></label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input required type="text" class="form-control" name="firstName" id="firstName" value="${sessionScope.user.firstName}" placeholder="<fmt:message key="label.firstName"/>" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="cols-sm-2 control-label"><fmt:message key="label.lastName"/></label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input required type="text" class="form-control" name="lastName" id="lastName" value="${sessionScope.user.lastName}" placeholder="<fmt:message key="label.lastName"/>" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="cols-sm-2 control-label"><fmt:message key="label.email"/></label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input required type="email" class="form-control" name="email" id="email" value="${sessionScope.user.email}" placeholder="<fmt:message key="label.email"/>" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="phone" class="cols-sm-2 control-label"><fmt:message key="label.phone"/></label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input required type="tel" class="form-control" name="phone" id="phone" value="${sessionScope.user.phone}" placeholder="<fmt:message key="label.phone"/>" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthDate" class="cols-sm-2 control-label"><fmt:message key="label.birthDate"/></label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input  required type="date" class="form-control" name="birthDate" id="birthDate" placeholder=<fmt:message key="label.birthDate"/> />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address" class="cols-sm-2 control-label"><fmt:message key="label.address"/></label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input required type="text" class="form-control" name="address" id="address" value="${sessionScope.user.address}" placeholder=<fmt:message key="label.address"/> />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="loginInput" class="cols-sm-2 control-label"><fmt:message key="label.login"/></label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input required type="text" class="form-control" name="login" id="loginInput"  value="${sessionScope.user.login}" placeholder=<fmt:message key="label.login"/> />
                                    </div>
                                </div>
                            </div>

                            <div class="form-group ">
                                <button type="submit" form="editUserInfo" class="btn btn-primary btn-lg btn-block login-button"><fmt:message key="button.save"/></button>
                            </div>
                            <input type="hidden" name="isEdit" value="0">
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
    </c:otherwise>
</c:choose>
    <c:choose>
        <c:when test="${param.isChangePassword || param.isChangePassword == null}">
            <button class="btn btn-dark"><a href="?isChangePassword=1" class="text-light"><fmt:message key="button.change.password"/></a></button>

        </c:when>
        <c:otherwise>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header"><fmt:message key="button.change.password"/></div>
                            <div class="card-body">

                                <form class="form-horizontal" method="post" action="/userInfo/change/password" id="changePassword">

                                    <div class="form-group">
                                        <label for="password" class="cols-sm-2 control-label"><fmt:message key="label.password"/></label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <input type="password" class="form-control" name="password" id="password" placeholder=<fmt:message key="label.password"/> />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="confirmPassword" class="cols-sm-2 control-label"><fmt:message key="label.confirm.password"/></label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder=<fmt:message key="label.confirm.password"/> />
                                            </div>
                                        </div>
                                        <c:if test="${sessionScope.notCorrectPassword != null}">
                                            <small><fmt:message key="error.password"/></small>
                                        </c:if>

                                        <c:if test="${sessionScope.passwordNotConfirmed != null}">
                                            <small ><fmt:message key="error.password.not.confirmed"/></small>
                                        </c:if>
                                    </div>
                                    <input type="hidden" name="isChangePassword" value="0">
                                    <div class="form-group ">
                                        <button type="submit" form="changePassword" class="btn btn-primary btn-lg btn-block login-button"><fmt:message key="button.change.password"/></button>
                                    </div>

                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>
