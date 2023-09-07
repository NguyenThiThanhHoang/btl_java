<%-- 
    Document   : addEmployer
    Created on : Sep 6, 2023, 10:43:04 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="form-floating mb-3 mt-3">
    <form:input type="text" class="form-control" path="nameCompany" id="nameCompany" placeholder="Tên công ty" name="nameCompany" />
    <label for="nameCompany">Tên công ty</label>
    <form:errors path="nameCompany" element="div" cssClass="text-danger" />
</div>

<div class="form-floating mb-3 mt-3">
    <form:input type="text" class="form-control" path="address" id="address" placeholder="Địa chỉ" name="address" />
    <label for="address">Địa chỉ</label>
    <form:errors path="address" element="div" cssClass="text-danger" />
</div>

<div class="form-floating mb-3 mt-3">
    <form:input type="number" class="form-control" path="tax" id="tax" placeholder="Mã số thuế" name="tax" />
    <label for="tax">Mã số thuế</label>
    
</div>

<div class="form-floating mb-3 mt-3">
    <form:input type="email" class="form-control" path="emailCompany" id="emailCompany" placeholder="email@gg.com" name="emailCompany" />
    <label for="emailCompany">Email</label>
    <form:errors path="emailCompany" element="div" cssClass="text-danger" />
</div>

<div class="form-floating mb-3 mt-3">
    <form:input type="text" class="form-control" path="phoneCompany" id="phoneCompany" placeholder="Số điện thoại" name="phoneCompany" />
    <label for="phoneCompany">Số điện thoại</label>
    
</div>

<div class="form-floating">
    <form:textarea class="form-control" id="description" name="description" path="description" placeholder="Mô tả công ty"></form:textarea>
        <label for="description">Mô tả công ty</label>
    <form:errors path="description" element="div" cssClass="text-danger" />
</div>
<div class="form-floating mb-3 mt-3">
    <div class="col-md-3">
        <form:input type="file" class="form-control" id="avatarCompany" path="fileCompany" />
        <label for="name">Avatar</label>
        
    </div>
</div>







