/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.controllers;

import com.apjob.components.JwtService;
import com.apjob.pojo.Candidate;
import com.apjob.pojo.Company;
import com.apjob.pojo.Employer;
import com.apjob.pojo.User;
import com.apjob.service.LocationService;
import com.apjob.service.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getEmail(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getEmail());
            
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/test/")
    @CrossOrigin(origins = {"127.0.0.1:5500"})
    public ResponseEntity<String> test(Principal pricipal) {
        return new ResponseEntity<>("SUCCESSFUL", HttpStatus.OK);
    }
    
    @PostMapping(path = "/addUser/", 
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<User> addUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar, @RequestPart MultipartFile avatarCompany) {
        User user = this.userService.addOrUpdateUserApi(params, avatar, avatarCompany);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> details(Principal user) {
        User u = this.userService.getUserByUn(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    
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
