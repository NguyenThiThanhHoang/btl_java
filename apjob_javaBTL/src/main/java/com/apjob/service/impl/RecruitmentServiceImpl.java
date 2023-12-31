/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.service.impl;

import com.apjob.pojo.Employer;
import com.apjob.pojo.RecruitmentNews;
import com.apjob.pojo.RecruitmentTag;
import com.apjob.pojo.Tag;
import com.apjob.pojo.User;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserRepository userRepo;

    @Autowired
    private RecruitmentTagRepository reTagRepo;

    @Override
    public List<RecruitmentNews> getRecruitmentNews(Map<String, String> params) {
        return this.recruitmentNewsRepo.getRecruitmentNews(params);
    }

    @Override
    public RecruitmentNews addOrUpdateRecruitmentNews(Map<String, String> params) {
        RecruitmentNews recruitment = new RecruitmentNews();
        RecruitmentTag recruitmentTag = new RecruitmentTag();

        int recruitmentId = Integer.parseInt(params.get("recruitmentId"));
      

        String tagIds = params.get("tags");
        String[] tagIdArray = tagIds.split(",");
        List<Integer> tagIdList = new ArrayList<>();

        for (String tagId : tagIdArray) {
            try {
                Integer id = Integer.parseInt(tagId);
                tagIdList.add(id);
            } catch (NumberFormatException e) {
                // Xử lý nếu có lỗi chuyển đổi
                e.printStackTrace();
            }
        }

        if (recruitmentId != 0) {
            recruitment.setId(recruitmentId);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepo.getUserByEmail(authentication.getName());

        if (u.getActive() == true) {
            Employer employer = this.emRepo.getEmployerById(u.getId());
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

            recruitment.setEmployer(employer);
            recruitment.setJobVanacy(params.get("jobVanacy"));
            recruitment.setSalary(params.get("salary"));
            recruitment.setTitle(params.get("title"));
            recruitment.setLocation(this.loRepo.getLocationById(Integer.parseInt(params.get("locationId"))));
            boolean result = this.recruitmentNewsRepo.addOrUpdateRecruitmentNews(recruitment);

            if (result) {
                for (Integer i : tagIdList) {
                    Tag tag = new Tag();
                    tag = this.tagRepo.getTagById(i);
                    if (tag != null) {
                        recruitmentTag.setTag(tag);
                        recruitmentTag.setTagName(tag.getName());
                        recruitmentTag.setRecruitment(recruitment);
                        this.reTagRepo.addRecuitmentTag(recruitmentTag);
                    }
                }
                return recruitment;
            } else {
                return recruitment;
            }

        } else {
            return recruitment;
        }

    }

    @Override
    public RecruitmentNews getRecruitmentNewsById(int i) {
        return this.recruitmentNewsRepo.getRecruitmentNewsById(i);
    }

//    @Override
//    public RecruitmentNews addOrUpdateRecruitmentNews(RecruitmentNews recruitment,int recruitmentId, List<String> tagIds) {
//        RecruitmentTag recruitmentTag = new RecruitmentTag();
//        recruitment.setCreatedDay(java.sql.Date.valueOf(LocalDate.now()));
//        
//        if (recruitmentId != 0) {
//            recruitment.setId(recruitmentId);
//        }
//        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User u = this.userRepo.getUserByEmail(authentication.getName());
//        recruitment.setEmployer(this.emRepo.getEmployerById(u.getId()));
//        boolean result = this.recruitmentNewsRepo.addOrUpdateRecruitmentNews(recruitment);
//         if (result){
//            for (String i : tagIds){
//                Tag tag = new Tag();
//                tag = this.tagRepo.getTagById(Integer.parseInt(i));
//                if (tag != null){
//                    recruitmentTag.setTag(tag);
//                    recruitmentTag.setTagName(tag.getName());
//                    recruitmentTag.setRecruitment(recruitment);
//                    this.reTagRepo.addRecuitmentTag(recruitmentTag);
//                }
//            }
//            return recruitment;
//        }else{
//            return recruitment;
//        }
//    }
}
