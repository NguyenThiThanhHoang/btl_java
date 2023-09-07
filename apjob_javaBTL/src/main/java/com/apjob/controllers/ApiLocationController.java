/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.controllers;

import com.apjob.pojo.Location;
import com.apjob.service.LocationService;
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
public class ApiLocationController {
    @Autowired
    private LocationService locationService;
    
    @GetMapping("/locations/")
    public ResponseEntity<List<Location>> list(Map<String, String> params) {
        return new ResponseEntity<>(this.locationService.getLocations(params), HttpStatus.OK);
    }
}
