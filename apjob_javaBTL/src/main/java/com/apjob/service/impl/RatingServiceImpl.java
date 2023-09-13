/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.service.impl;

import com.apjob.pojo.Rating;
import com.apjob.pojo.User;
import com.apjob.repository.CandidateRepository;
import com.apjob.repository.RatingRepository;
import com.apjob.repository.UserRepository;
import com.apjob.service.RatingService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class RatingServiceImpl implements RatingService{
    
    @Autowired
    private RatingRepository raRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private CandidateRepository caRepo;

    @Override
    public List<Rating> getRatings(int companyId) {
        return this.raRepo.getRatings(companyId);
    }

    @Override
    public Rating addRating(Rating r) {
        r.setCreatedDay(java.sql.Date.valueOf(LocalDate.now()));
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepo.getUserByEmail(authentication.getName());
        r.setCandidate(this.caRepo.getCandidateById(u.getId()));
        
        return this.raRepo.addOrUpdateRating(r);
    }
    
}
