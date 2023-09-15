<%-- 
    Document   : header
    Created on : Jul 22, 2023, 3:11:35 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="/" var="action" />
<div class="row">
    <div class="mini-menu col-md-1">
        <button class="toggle-button" id="toggle-button">☰</button>
        <ul class="menu-list" id="menu-list">
            <li><a href="#">Mục 1</a></li>
            <li><a href="#">Mục 2</a></li>
            <li><a href="#">Mục 3</a></li>
        </ul>
    </div>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <div class="container-fluid col-md-11">
            <a class="navbar-brand" href="${action}#">E-commerce Website</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${action}#">Trang chủ</a>
                    </li>

                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
  <%--                           <c:set var="username" value="${fn:substringBefore(pageContext.request.userPrincipal.name, '@')}" /> 
                            <li class="nav-item">
                                <a class="nav-link text-info" href="<c:url value="/profile" />">${username}</a>
                            </li>
  --%>

                            <li class="nav-item">
                                <a class="nav-link text-info" href="<c:url value="/profile" />">${user.name}</a>
                            </li>
                            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                                <li class="nav-item">
                                    <a class="nav-link" href="<c:url value="/addUser" />">Thêm người dùng</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-danger" href="<c:url value="/stats" />">Báo cáo</a>
                                </li>
                            </c:if>

                            <li class="nav-item">
                                <a class="nav-link text-danger" href="<c:url value="/logout" />">Đăng xuất</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link text-info" href="<c:url value="/" />">HELLO!</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/login" />">Đăng nhập</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <form class="d-flex" action="${action}">
                    <input class="form-control me-2" name="kw" type="text" placeholder="Nhập từ khóa...">
                    <button class="btn btn-primary" type="submit">Tìm</button>
                </form>
            </div>
        </div>
    </nav>
</div>
<script src="<c:url value="/resources/js/main.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>