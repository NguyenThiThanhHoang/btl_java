/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;

import com.apjob.pojo.CV;
import com.apjob.pojo.RecruitmentNews;
import com.apjob.repository.RecruitmentNewsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class RecruitmentNewsRepositoryImpl implements RecruitmentNewsRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<RecruitmentNews> getRecruitmentNews(Map<String, String> params) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<RecruitmentNews> criteriaQuery = criteriaBuilder.createQuery(RecruitmentNews.class);
        Root root = criteriaQuery.from(RecruitmentNews.class);
        criteriaQuery.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), String.format("%%%s%%", kw)));
            }

            String jobVacancy = params.get("jobVacancy");
            if (jobVacancy != null && !jobVacancy.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("job_vacancy"), String.format("%%%s%%", jobVacancy)));
            }

            String location = params.get("location");
            if (location != null && !location.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("location").get("location_name"), String.format("%%%s%%", location)));
            }

            String salary = params.get("salary");
            if (salary != null && !salary.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("salary"), String.format("%%%s%%", salary)));
            }

            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        Query query = session.createQuery(criteriaQuery);

        if (params != null) {
            String page = params.get("page");
            if (page != null) {
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE_LARGE_ITEM"));
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public int countRecruitmentNews() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM RecruitmentNews");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateRecruitmentNews(RecruitmentNews r) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        try {
            if (r.getId() == null) {
                s.save(r);
            } else {
                s.update(r);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public RecruitmentNews getRecruitmentNewsById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(RecruitmentNews.class, id);
    }

    @Override
    public boolean deleteRecruitmentNews(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        RecruitmentNews r = this.getRecruitmentNewsById(id);
        try {
            s.delete(r);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
