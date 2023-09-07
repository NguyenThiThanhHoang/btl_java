/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
/* global togglePasswordButton, passwordInput */


document.addEventListener("DOMContentLoaded", function () {
    // JavaScript để xử lý sự kiện khi nhấp vào nút thu nhỏ/phóng to
    var toggleButton = document.getElementById("toggle-button");
    var miniMenu = document.querySelector(".mini-menu");

    toggleButton.addEventListener("click", function () {
        miniMenu.classList.toggle("open");

    });

//    //Ẩn, hiện pass
//    var buttonShowHide = document.getElementById("togglePassword");
//    var passwordInput = document.getElementById("password");
//    var isPasswordVisible = false;
//
//    buttonShowHide.addEventListener("click", function () {
//        isPasswordVisible = !isPasswordVisible;
//        if (isPasswordVisible) {
//            passwordInput.type = "text";
//            buttonShowHide.textContent = "Hide";
//        } else {
//            passwordInput.type = "password";
//            buttonShowHide.textContent = "Show";
//        }
//    });
  
    
    //Thêm form con-> add User
    var selectRole = document.getElementById("userRole");
    var employerForm = document.getElementById("employerForm");
    var candidateForm = document.getElementById("candidateForm");
    
    selectRole.addEventListener("change", function(){
        if(selectRole.value === "CANDIDATE"){
            employerForm.style.display = "none";
            candidateForm.style.display = "block";
        } else if (selectRole.value === "EMPLOYER"){
            employerForm.style.display = "block";
            candidateForm.style.display = "none";
        }else{
            employerForm.style.display = "none";
            candidateForm.style.display = "none";
        }
    });
});





