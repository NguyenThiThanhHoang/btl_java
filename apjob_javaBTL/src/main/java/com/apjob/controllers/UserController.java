/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.controllers;

import com.apjob.pojo.Candidate;
import com.apjob.pojo.Company;
import com.apjob.pojo.Employer;
import com.apjob.pojo.Tag;
import com.apjob.pojo.User;
import com.apjob.service.LocationService;
import com.apjob.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    private UserService userService;

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
        if (!rs.hasErrors()) {
            if (this.userService.addOrUpdateUser(user) == true) {
                return "redirect:/";
            } else {
                return "index";
            }
        }
        return "index";
    }

}
