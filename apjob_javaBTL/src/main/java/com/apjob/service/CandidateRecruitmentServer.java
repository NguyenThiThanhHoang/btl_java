/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.service;

import com.apjob.pojo.CV;
import com.apjob.pojo.CandidateRecruitment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface CandidateRecruitmentServer {
    List<CandidateRecruitment> getCandidateRecruitments(int recruitment);

    CandidateRecruitment addCandidateRecruitment(Map<String, String> params);
}
