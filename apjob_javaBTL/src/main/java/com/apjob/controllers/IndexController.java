/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.controllers;

import com.apjob.service.LocationService;
import com.apjob.service.TagService;
import com.apjob.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private Environment env;

    @ModelAttribute
    public void commonAttr(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("locations", this.locationService.getLocations(params));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Lấy thông tin người dùng từ UserDetails
            String username = userDetails.getUsername();
            model.addAttribute("user", this.userService.getUserByUn(username));
        }
    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("employers", this.userService.getEmployersFalse(params));

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE_SMALL_ITEM"));
        long count = this.userService.countEmployersFalse();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "index";
    }
    
    @PostMapping("/")
    public String approveApplication(@RequestParam("employerId") int employerId){
        boolean result = this.userService.updateActive(employerId);
        if (!result){
            return "index";
        }
        return "redirect:/";
    }
}
