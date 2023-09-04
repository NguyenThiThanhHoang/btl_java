/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.RecruitmentNews;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface RecruitmentNewsRepository {
    List<RecruitmentNews> getRecruitmentNews(Map<String, String> params);
    int countRecruitmentNews();
    boolean addOrUpdateRecruitmentNews(RecruitmentNews r);
    RecruitmentNews getRecruitmentNewsById(int id);
    boolean deleteRecruitmentNews(int id);    
}
