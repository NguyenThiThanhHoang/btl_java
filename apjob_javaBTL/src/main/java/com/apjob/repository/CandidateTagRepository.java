/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.CandidateTag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface CandidateTagRepository {
    List<CandidateTag> getCandidateTags(Map<String, String> params);
    int countCandidateTags(Map<String, String> params);
    boolean addCandidateTag(CandidateTag c);
    List<CandidateTag> getCandidateTagById(String candidateId, String tagId); 
    boolean deleteCandidateTagById(String candidateId, String tagId); 
}
