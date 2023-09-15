/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.controllers;

import com.apjob.service.StatsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class StatsController {

    @Autowired
    private StatsService sService;

    @RequestMapping("/stats")
    public String stats(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("stats", this.sService.statsRevenue(params));

        return "stats";
    }

    @PostMapping("/stats")
    public String approveApplication(Model model,@RequestParam Map<String, String> params) {
        model.addAttribute("stats", this.sService.statsRevenue(params));
        return "stats";
    }
}
