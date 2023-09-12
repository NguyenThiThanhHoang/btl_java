/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.service.impl;

import com.apjob.pojo.Employer;
import com.apjob.pojo.RecruitmentNews;
import com.apjob.pojo.RecruitmentTag;
import com.apjob.pojo.Tag;
import com.apjob.repository.EmployerRepository;
import com.apjob.repository.LocationRepository;
import com.apjob.repository.RecruitmentNewsRepository;
import com.apjob.repository.RecruitmentTagRepository;
import com.apjob.repository.TagRepository;
import com.apjob.repository.UserRepository;
import com.apjob.service.RecruitmentService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    @Autowired
    private RecruitmentNewsRepository recruitmentNewsRepo;
    
    @Autowired
    private EmployerRepository emRepo;
    
    @Autowired
    private LocationRepository loRepo;
    
    @Autowired
    private TagRepository tagRepo;
    
    @Autowired
    private RecruitmentTagRepository reTagRepo;

    @Override
    public List<RecruitmentNews> getRecruitmentNews(Map<String, String> params) {
        return this.recruitmentNewsRepo.getRecruitmentNews(params);
    }

    @Override
    public RecruitmentNews addOrUpdateRecruitmentNews(Map<String, String> params, int recruitmentId, int employerId, List<String> tagIds) {
        RecruitmentNews recruitment = new RecruitmentNews();
        RecruitmentTag recruitmentTag = new RecruitmentTag();

        if (recruitmentId != -1) {
            recruitment.setId(recruitmentId);
        }

        String dateString = params.get("deadline");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date utilDate = dateFormat.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            recruitment.setDeadline(sqlDate);

        } catch (ParseException ex) {
            recruitment.setDeadline(java.sql.Date.valueOf(LocalDate.now()));
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        recruitment.setCreatedDay(java.sql.Date.valueOf(LocalDate.now()));
        recruitment.setDescription(params.get("des"));
        recruitment.setEmployer(this.emRepo.getEmployerById(employerId));
        recruitment.setJobVanacy(params.get("jobVanacy"));
        recruitment.setSalary(params.get("salary"));
        recruitment.setTitle(params.get("title"));
        recruitment.setLocation(this.loRepo.getLocationById(Integer.parseInt(params.get("locationId"))));
        
        boolean result = this.recruitmentNewsRepo.addOrUpdateRecruitmentNews(recruitment);
        
        if (result){
            for (String i : tagIds){
                Tag tag = new Tag();
                tag = this.tagRepo.getTagById(Integer.parseInt(i));
                if (tag != null){
                    recruitmentTag.setTag(tag);
                    recruitmentTag.setTagName(tag.getName());
                    recruitmentTag.setRecruitment(recruitment);
                    this.reTagRepo.addRecuitmentTag(recruitmentTag);
                }
            }
            return recruitment;
        }else{
            return recruitment;
        }
       
    }

    @Override
    public RecruitmentNews getRecruitmentNewsById(int i) {
        return this.recruitmentNewsRepo.getRecruitmentNewsById(i);
    }

}
