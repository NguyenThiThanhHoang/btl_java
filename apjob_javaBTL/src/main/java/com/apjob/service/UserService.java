/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.service;

import com.apjob.pojo.Candidate;
import com.apjob.pojo.Company;
import com.apjob.pojo.Employer;
import com.apjob.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
public interface UserService extends UserDetailsService{
    List<User> getAdmins(Map<String, String> params);
    int countAdmins();
    
    boolean addOrUpdateUser(User u);
    
    List<Employer> getEmployers(Map<String, String> params);
    int countEmployers();
    
    List<Candidate> getCandidates(Map<String, String> params);
    int countCandidates();
  
    List<Company> getCompanys(Map<String, String> params);
    int countCompanyes();
  
    User getUserByUn(String username);
    boolean authUser(String username, String password);
    
    User addOrUpdateUserApi(Map<String, String> params, MultipartFile avatar, MultipartFile avatarCompany);
    
}
