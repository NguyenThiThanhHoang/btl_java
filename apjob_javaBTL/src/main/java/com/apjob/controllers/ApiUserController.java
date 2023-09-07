/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.controllers;

import com.apjob.pojo.Candidate;
import com.apjob.pojo.Company;
import com.apjob.pojo.Employer;
import com.apjob.pojo.User;
import com.apjob.service.LocationService;
import com.apjob.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserService userService;

    
    @GetMapping("/users/")
    public ResponseEntity<List<User>> listUser(Map<String, String> params) {
        return new ResponseEntity<>(this.userService.getAdmins(params), HttpStatus.OK);
    }
    
    @GetMapping("/candidates/")
    public ResponseEntity<List<Candidate>> listCandidate(Map<String, String> params) {
        return new ResponseEntity<>(this.userService.getCandidates(params), HttpStatus.OK);
    }
    
    @GetMapping("/employers/")
    public ResponseEntity<List<Employer>> listEmployer(Map<String, String> params) {
        return new ResponseEntity<>(this.userService.getEmployers(params), HttpStatus.OK);
    }
    
    @GetMapping("/companys/")
    public ResponseEntity<List<Company>> listCompany(Map<String, String> params) {
        return new ResponseEntity<>(this.userService.getCompanys(params), HttpStatus.OK);
    }
}
