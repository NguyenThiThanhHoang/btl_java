<%-- 
    Document   : index
    Created on : Aug 31, 2023, 12:16:54 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">
        <hr>
        <h1 class="text-center text-danger mt-1">CÔNG VIỆC CẦN LÀM</h1>
        <div class="container mt-3">

            <h2>Employers - Companies Table</h2>
            <p>Bảng các nhà tuyển dụng, công ty cần duyệt:</p>

            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="text-center" colspan="4">Employer</th>
                            <th class="text-center" colspan="5">Company</th>
                            <th></th>
                        </tr>
                        <tr>
                            <th class="text-center">ID</th>
                            <th class="text-center">Tên</th>
                            <th class="text-center">Email</th>
                            <th class="text-center">Phone</th>
                            <th class="text-center">Tên công ty</th>
                            <th class="text-center">Địa chỉ</th>
                            <th class="text-center">MST</th>
                            <th class="text-center">Email</th>
                            <th class="text-center">phone</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center">ID</td>
                            <td class="text-center">Tên</td>
                            <td class="text-center">Email</td>
                            <td class="text-center">Phone</td>
                            <td class="text-center">Tên công ty</td>
                            <td class="text-center">Địa chỉ</td>
                            <td class="text-center">MST</td>
                            <td class="text-center">Email</td>
                            <td class="text-center">phone</td>
                            <td class="text-center"><button type="button" class="btn btn-primary">Duyệt</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h2 class="text-center text-success mt-1">Welcome to our AP career build page!!!</h2>
    </c:otherwise>
</c:choose>

