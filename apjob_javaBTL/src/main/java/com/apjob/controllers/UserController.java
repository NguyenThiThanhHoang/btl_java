/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.controllers;

import com.apjob.pojo.Company;
import com.apjob.pojo.User;
import com.apjob.repository.CompanyRepository;
import com.apjob.repository.UserRepository;
import com.apjob.service.LocationService;
import com.apjob.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
@ControllerAdvice
public class UserController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyRepository companyRepo;

    @ModelAttribute
    public void commonAttr(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("locations", this.locationService.getLocations(params));
    }

    @GetMapping("/addUser")
    public String list(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String Register(@ModelAttribute("user") @Valid User user,
            BindingResult rs) {
        if (rs.hasErrors()) {
            for (ObjectError error : rs.getAllErrors()) {
            System.out.println("Lỗi: " + error.getDefaultMessage());
        }
            User checkUser = this.userRepo.getUserByEmail(user.getEmail());
            Company checkCoEmail = null;
            Company checkCoTax = null;
            if (user.getEmailCompany() != null && user.getTax() != null) {
                checkCoEmail = this.companyRepo.getCompanyByEmail(user.getEmailCompany());
                checkCoTax = this.companyRepo.getCompanyByTax(user.getTax());
            }
            if (checkUser == null && checkCoEmail == null && checkCoTax == null) {
                if (this.userService.addOrUpdateUser(user) == true) {
                    return "redirect:/";
                } else {
                    //thêm lỗi
                    return "redirect:/";
                }

            } else {
                //viết c\nsole lỗi
                return "addUser";
            }
        }
        return "redirect:/addUser";
    }

}
