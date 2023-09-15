/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.service.impl;

import com.apjob.pojo.CandidateRecruitment;
import com.apjob.pojo.RecruitmentNews;
import com.apjob.pojo.User;
import com.apjob.repository.CVRepository;
import com.apjob.repository.CandidateRecruitmentRepository;
import com.apjob.repository.CandidateRepository;
import com.apjob.repository.RecruitmentNewsRepository;
import com.apjob.repository.UserRepository;
import com.apjob.service.CandidateRecruitmentServer;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class CandidateRecruitmentServerImpl implements CandidateRecruitmentServer {

    @Autowired
    private CandidateRecruitmentRepository candidateRecruitmentRepo;

    @Autowired
    private RecruitmentNewsRepository reRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private CandidateRepository caRepo;
    
    @Autowired
    private CVRepository cvRepo;

    @Override
    public List<CandidateRecruitment> getCandidateRecruitments(int recruitment) {
        return this.candidateRecruitmentRepo.getCandidateRecruitments(recruitment);
    }

    @Override
    public CandidateRecruitment addCandidateRecruitment(Map<String, String> params) {
        CandidateRecruitment candidateRecruitment = new CandidateRecruitment();
        RecruitmentNews r = new RecruitmentNews();

        r = this.reRepo.getRecruitmentNewsById(Integer.parseInt(params.get("recruitmentId")));

        if (r != null) {
            candidateRecruitment.setRecruitment(r);
            candidateRecruitment.setEmployer(r.getEmployer());
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User u = this.userRepo.getUserByEmail(authentication.getName());
            candidateRecruitment.setCandidate(this.caRepo.getCandidateById(u.getId()));
            
            candidateRecruitment.setTextMail(params.get("textMail"));
            candidateRecruitment.setLinkCV(this.cvRepo.getCVById(Integer.parseInt(params.get("cvId"))).getLinkCV());
            candidateRecruitment.setCreatedDay(java.sql.Date.valueOf(LocalDate.now()));
            this.candidateRecruitmentRepo.addCandidateRecruitment(candidateRecruitment);
        }

        return candidateRecruitment;
    }

}
