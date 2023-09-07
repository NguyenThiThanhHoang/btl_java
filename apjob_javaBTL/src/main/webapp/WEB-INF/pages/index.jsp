<%-- 
    Document   : index
    Created on : Aug 31, 2023, 12:16:54 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chu</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <ul>
            <c:forEach items="${candidates}" var="t">
                <li>${t.schoolName} - ${t.birthDay}</li>
            </c:forEach>
        </ul>
    </body>
</html>