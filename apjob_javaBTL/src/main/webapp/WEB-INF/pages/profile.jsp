<%-- 
    Document   : menu
    Created on : Sep 14, 2023, 11:11:05 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container mt-5">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">User Profile</h3>
                    </div>
                    <div class="card-body">
                        <div class="text-center">
                            <img src="https://res.cloudinary.com/de3ifjaz1/image/upload/v1694107072/i7owhuwhcx0xv8qtehs0.jpg" class="rounded" alt="Avatar" width="150" height="150">
                        </div>
                        <p><strong>Name:</strong> ${user.name}</p>
                        <p><strong>Username:</strong> ${user.email}</p>
                        <p><strong>Phone:</strong> ${user.phone}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

