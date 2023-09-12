/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.RecruitmentTag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface RecruitmentTagRepository {
    List<RecruitmentTag> getRecuitmentTags(Map<String, String> params);
    int countRecuitmentTags(Map<String, String> params);
    boolean addRecuitmentTag(RecruitmentTag r);
    List<RecruitmentTag> getRecuitmentTagById(String recuitmentId, String tagId); 
    boolean deleteRecuitmentTagById(String recuitmentId, String tagId); 
}
