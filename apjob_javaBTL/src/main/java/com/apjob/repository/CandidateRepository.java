/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.Candidate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface CandidateRepository {
    List<Candidate> getCandidates(Map<String, String> params);
    int countCandidates();
    boolean addOrUpdateCandidate(Candidate c);
    Candidate getCandidateById(int id);
    boolean deleteCandidate(int id);
}
