/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.controllers;

import com.apjob.pojo.Tag;
import com.apjob.service.TagService;
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
public class ApiTagController {
    @Autowired
    private TagService tagService;
    
    @GetMapping("/tags/")
    public ResponseEntity<List<Tag>> list(Map<String, String> params) {
        return new ResponseEntity<>(this.tagService.getTags(params), HttpStatus.OK);
    }
}
