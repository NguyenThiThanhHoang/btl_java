/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.service.impl;

import com.apjob.pojo.Candidate;
import com.apjob.pojo.Company;
import com.apjob.pojo.Employer;
import com.apjob.pojo.Location;
import com.apjob.pojo.User;
import com.apjob.repository.CandidateRepository;
import com.apjob.repository.CompanyRepository;
import com.apjob.repository.EmployerRepository;
import com.apjob.repository.LocationRepository;
import com.apjob.repository.UserRepository;
import com.apjob.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private EmployerRepository emRepo;
    
    @Autowired
    private CandidateRepository caRepo;
    
    @Autowired
    private CompanyRepository comRepo;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    
    @Autowired
    private LocationRepository loRepo;


    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> getAdmins(Map<String, String> params) {
        return this.userRepo.getUsers();
    }

    @Override
    public int countAdmins() {
        return this.userRepo.countUsers();
    }

    @Override
    public boolean addOrUpdateUser(User u) {
        Company company = new Company();
        Employer employer = new Employer();
        Candidate candidate = new Candidate();
        Location location = new Location();
        Boolean addResult = false;
        
        if (!u.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (!u.getFileCompany().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFileCompany().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                company.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String pass = u.getPassword();
        u.setPassword(this.passEncoder.encode(pass));
        Boolean addUserResult = userRepo.addOrUpdateUser(u);
        int userId = u.getId();
        
        if (u.getUserRole().toString().equals("CANDIDATE")){
            candidate.setId(userId);
            
            location = this.loRepo.getLocationById(u.getLocationId());
            
            candidate.setLocation(location);
            candidate.setSchoolName(u.getSchoolName());
            candidate.setBirthDay(u.getBirthDay());
            addResult = caRepo.addOrUpdateCandidate(candidate);
            return addResult;
        } else if(u.getUserRole().toString().equals("EMPLOYER")){
            company.setNameCompany(u.getNameCompany());
            company.setAddress(u.getAddress());
            company.setTax(u.getTax());
            company.setEmailCompany(u.getEmailCompany());
            company.setPhoneCompany(u.getPhoneCompany());
            company.setDescription(u.getDescription());
            comRepo.addOrUpdateCompany(company);
            
            employer.setId(userId);
            employer.setCompany(company);
            addResult = emRepo.addOrUpdateEmployer(employer);
            return addResult;
        }else{
            return addUserResult;
        }
    }

    @Override
    public List<Employer> getEmployers(Map<String, String> params) {
        return this.emRepo.getEmployers(params);
    }

    @Override
    public int countEmployers() {
        return this.emRepo.countEmployers();
    }

    @Override
    public List<Candidate> getCandidates(Map<String, String> params) {
        return this.caRepo.getCandidates(params);
    }

    @Override
    public int countCandidates() {
        return this.caRepo.countCandidates();
    }

    @Override
    public List<Company> getCompanys(Map<String, String> params) {
        return this.comRepo.getCompanys(params);
    }

    @Override
    public int countCompanyes() {
        return this.comRepo.countCompanys();
    }

}
