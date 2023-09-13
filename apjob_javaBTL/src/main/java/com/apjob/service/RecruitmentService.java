/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.service;

import com.apjob.pojo.RecruitmentNews;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface RecruitmentService {
    List<RecruitmentNews> getRecruitmentNews(Map<String, String> params);
    RecruitmentNews addOrUpdateRecruitmentNews(RecruitmentNews recruitment, int recruitmentId, List<String> tagIds);
    RecruitmentNews getRecruitmentNewsById (int id);
}
