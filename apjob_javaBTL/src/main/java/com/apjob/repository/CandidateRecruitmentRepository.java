/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.CandidateRecruitment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface CandidateRecruitmentRepository {
    List<CandidateRecruitment> getCandidateRecruitments(int recruitmentId);
    int countCandidateRecruitments(Map<String, String> params);
    boolean addCandidateRecruitment(CandidateRecruitment c);
    List<CandidateRecruitment> getCandidateRecruitmentById(int id); //get recuitmentID
    boolean deleteCandidateRecruitmentById(int id); //get recuitmentID
    
}
