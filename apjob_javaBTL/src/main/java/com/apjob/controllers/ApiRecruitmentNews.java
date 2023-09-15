/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.controllers;

import com.apjob.pojo.CandidateRecruitment;
import com.apjob.pojo.RecruitmentNews;
import com.apjob.service.CandidateRecruitmentServer;
import com.apjob.service.RecruitmentService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ApiRecruitmentNews {

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private CandidateRecruitmentServer candidateRecruitmentService;

    @PostMapping(path = "/addOrUpdateRecruitment/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<RecruitmentNews> addOrUpdateRecruitment(@RequestParam Map<String, String> params) {
        RecruitmentNews recruitment = this.recruitmentService.addOrUpdateRecruitmentNews(params);
        return new ResponseEntity<>(recruitment, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/recruitmentNews/{recruitmentId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<RecruitmentNews> details(@PathVariable(value = "recruitmentId") int id) {
        return new ResponseEntity<>(this.recruitmentService.getRecruitmentNewsById(id), HttpStatus.OK);
    }

    @RequestMapping(path= "/recruitments/",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<List<RecruitmentNews>> listRecruitmentNews(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.recruitmentService.getRecruitmentNews(params), HttpStatus.OK);
    }

    @PostMapping(path = "/addCVForRecruiment/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<CandidateRecruitment> addCVForRecruiment(@RequestParam Map<String, String> params) {
        CandidateRecruitment cr = this.candidateRecruitmentService.addCandidateRecruitment(params);
        return new ResponseEntity<>(cr, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/recruitmentNews/{recruitmentId}-cvs/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<CandidateRecruitment>> listCVForRecruitment(@PathVariable(value = "recruitmentId") int id) {
        return new ResponseEntity<>(this.candidateRecruitmentService.getCandidateRecruitments(id), HttpStatus.OK);
    }

}
