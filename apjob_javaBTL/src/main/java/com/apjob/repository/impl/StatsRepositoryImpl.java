/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;

import com.apjob.pojo.CandidateRecruitment;
import com.apjob.pojo.RecruitmentTag;
import com.apjob.pojo.Tag;
import com.apjob.repository.StatsRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Transactional
@PropertySource("classpath:configs.properties")
@Repository
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private SimpleDateFormat f;

    @Override
    public JSONArray statsRevenue(Map<String, String> params) {
        List<Object[]> ArrayList = null;
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

// Tạo các root để tham gia bảng
        Root<Tag> tagRoot = criteriaQuery.from(Tag.class);
        Root<RecruitmentTag> recruitmentTagRoot = criteriaQuery.from(RecruitmentTag.class);
        Root<CandidateRecruitment> candidateRecruitmentRoot = criteriaQuery.from(CandidateRecruitment.class);

// Thiết lập các điều kiện trong câu truy vấn
        criteriaQuery.multiselect(tagRoot.get("name"), criteriaBuilder.count(candidateRecruitmentRoot.get("linkCV")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(tagRoot.get("id"), recruitmentTagRoot.get("tag").get("id")));
        predicates.add(criteriaBuilder.equal(recruitmentTagRoot.get("recruitment").get("id"), candidateRecruitmentRoot.get("recruitment").get("id")));

        String fd = params.get("fromDate");
        if (fd != null && !fd.isEmpty()) {
            try {
                Path<Date> createdDatePath = candidateRecruitmentRoot.get("createdDay");
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(createdDatePath, f.parse(fd)));
            } catch (ParseException ex) {
                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String td = params.get("toDate");
        if (td != null && !td.isEmpty()) {
            try {
                Path<Date> createdDatePath = candidateRecruitmentRoot.get("createdDay");
                predicates.add(criteriaBuilder.lessThanOrEqualTo(createdDatePath, f.parse(td)));
            } catch (ParseException ex) {
                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String quarter = params.get("quarter");
        if (quarter != null && !quarter.isEmpty()) {
            String year = params.get("year");
            if (year != null && !year.isEmpty()) {
                predicates.addAll(Arrays.asList(
                        criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class, candidateRecruitmentRoot.get("createdDay")), Integer.parseInt(year)),
                        criteriaBuilder.equal(criteriaBuilder.function("QUARTER", Integer.class, candidateRecruitmentRoot.get("createdDay")), Integer.parseInt(quarter))
                ));
            }
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        criteriaQuery.groupBy(tagRoot.get("id"));

// Thực hiện câu truy vấn
        TypedQuery<Object[]> query = session.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();

        JSONArray dataJSONArray = new JSONArray();
        for (Object[] result : results) {
            String tagName = (String) result[0];
            Long linkCVCount = (Long) result[1];

            JSONObject dataPoint = new JSONObject();
            dataPoint.put("tagName", tagName);
            dataPoint.put("linkCVCount", linkCVCount);

            dataJSONArray.put(dataPoint);

        }

        return dataJSONArray;
    }
}
