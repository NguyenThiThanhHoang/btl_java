<%-- 
    Document   : addCandidate
    Created on : Sep 6, 2023, 10:43:14 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="form-floating mb-3 mt-3">
    <form:input type="text" class="form-control" path="schoolName" id="schoolName" placeholder="Đại học/cao đẳng" name="schoolName" />
    <label for="schoolName">Trường đại học/cao đẳng</label>
    <form:errors path="schoolName" element="div" cssClass="text-danger" />
</div>


<div class="form-floating mb-3 mt-3">
    <form:input type="date" class="form-control" path="birthDay" id="birthDay" placeholder="Ngày tháng năm sinh" name="birthDay" />
    <label for="birthDay">Ngày tháng năm sinh</label>
    <form:errors path="birthDay" element="div" cssClass="text-danger" />
</div>

<div class="form-floating mt-3 mb-3">

    <div class="dropdown">
        <label for="location">Tỉnh/TP</label>
        <form:select path="location" class="form-select" name="location" id="location">
            <c:forEach items="${locations}" var="l">
                <form:option value="${l.id}">${l.name}</form:option>
            </c:forEach>
        </form:select>
    </div>
</div>

