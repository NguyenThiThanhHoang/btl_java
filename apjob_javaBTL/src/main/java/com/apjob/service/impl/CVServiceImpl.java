
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.service.impl;

import com.apjob.pojo.CV;
import com.apjob.pojo.Candidate;
import com.apjob.pojo.User;
import com.apjob.repository.CVRepository;
import com.apjob.repository.CandidateRepository;
import com.apjob.repository.UserRepository;
import com.apjob.service.CVService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Service
public class CVServiceImpl implements CVService{
    
    @Autowired
    private CVRepository cvRepo;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private CandidateRepository caRepo;
    @Override
    public List<CV> getCVs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepo.getUserByEmail(authentication.getName());
        return this.cvRepo.getCVs(u.getId());
    }

    @Override
    public CV addOrUpdateCV(Map<String, String> params, @RequestPart MultipartFile file) {
        CV cv = new CV();
        
        if (!file.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(file.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                cv.setLinkCV(res.get("secure_url").toString());
                
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cv.setNameCV(params.get("nameFile"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepo.getUserByEmail(authentication.getName());
        cv.setCandidate(this.caRepo.getCandidateById(u.getId()));
        this.cvRepo.addOrUpdateCV(cv);
        return cv;
    }
    
}
