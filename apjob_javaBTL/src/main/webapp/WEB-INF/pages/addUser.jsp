<%-- 
    Document   : addUser
    Created on : Sep 6, 2023, 1:15:31 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-info text-center mt-1">Thêm người dùng mới</h1>

<c:url value="/addUser" var="action" />
<form:form modelAttribute="user" method="post" action="${action}" enctype="multipart/form-data">
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Họ và tên" name="name" />
        <label for="name">Họ và tên</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="email" class="form-control" path="email" id="email" placeholder="email@gg.com" name="email" />
        <label for="email">Email</label>
        <form:errors path="email" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="phone" id="phone" placeholder="Số điện thoại" name="phone" />
        <label for="phone">Số điện thoại</label>
       
    </div>

    <div class="form-floating mt-3 mb-3">
        <form:input type="password" class="form-control" path="password" id="password" placeholder="Mật khẩu" name="password" />
        <label for="password">Nhập mật khẩu</label>
        <form:errors path="password" element="div" cssClass="text-danger" />
    </div>


    <div class="row">
        <div class="form-floating mt-3 mb-3">
            <div class="row">
                <div class="col-md-3">
                    <div class="dropdown">
                        <form:select path="userRole" class="form-select" name="userRole" id="userRole">
                            <form:option value="ADMIN">Admin</form:option>
                            <form:option value="EMPLOYER">Nhà tuyển dụng</form:option>
                            <form:option value="CANDIDATE">Ứng viên</form:option>
                        </form:select>
                        
                    </div>
                </div>
                <div class="col-md-1">
                    <div class="form-check form-switch">
                        <form:checkbox path="active" id="active" class="form-check-input" value="true" name="active" checked="checked" />
                        <label class="form-check-label" for="active">Active</label>
                    </div>
                </div>
                <div class="col-md-3">
                    <form:input type="file" class="form-control" path="file" id="avatar" />
                    <label for="name">Ảnh đại diện</label>
                </div>
            </div>
        </div>
    </div>
    <div id="candidateForm" style="display: none;">
        <jsp:include page="addCandidate.jsp" />
    </div>
    <div id="employerForm" style="display: none;">
        <jsp:include page="addEmployer.jsp" />
    </div>
    <div class="form-floating mt-3 mb-3">
        <button type="submit" id="submitButton" class="btn btn-info">Thêm người dùng</button>
    </div>
</form:form>
<script src="<c:url value="/resources/js/main.js" />"></script>
