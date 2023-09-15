<%-- 
    Document   : index
    Created on : Aug 31, 2023, 12:16:54 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">
        <hr>
        <h1 class="text-center text-danger mt-1">CÔNG VIỆC CẦN LÀM</h1>
        <div class="container mt-3">

            <h2>Employers - Companies Table</h2>
            <p>Danh sách các nhà tuyển dụng, công ty cần duyệt:</p>

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
                        <c:forEach items="${employers}" var="e">
                            <tr>
                                <td class="text-center">${e.id}</td>
                                <td class="text-center">${e.user.name}</td>
                                <td class="text-center">${e.user.email}</td>
                                <td class="text-center">${e.user.phone}</td>
                                <td class="text-center">${e.company.nameCompany}</td>
                                <td class="text-center">${e.company.address}</td>
                                <td class="text-center">${e.company.tax}</td>
                                <td class="text-center">${e.company.emailCompany}</td>
                                <td class="text-center">${e.company.phoneCompany}</td>

                                <td class="text-center">
                                    <c:url value="/" var="action" />
                                    <form:form method="post" action="${action}">
                                        <input type="hidden" name="employerId" value="${e.id}" />
                                        <button type="submit" id="submitButton" class="btn btn-primary">Duyệt</button>
                                    </form:form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <c:if test="${counter > 1}">
            <ul class="pagination mt-1">
                <li class="page-item"><a class="page-link" href="<c:url value="/" />">Tất cả</a></li>
                    <c:forEach begin="1" end="${counter}" var="i">
                        <c:url value="/" var="pageUrl">
                            <c:param name="page" value="${i}"></c:param>
                        </c:url>
                    <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                    </c:forEach>
            </ul>
        </c:if>
    </c:when>
    <c:otherwise>
        <h2 class="text-center text-success mt-1">Welcome to our AP career build page!!!</h2>
    </c:otherwise>
</c:choose>


