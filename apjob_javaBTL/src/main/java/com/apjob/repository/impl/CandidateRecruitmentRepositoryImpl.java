/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;

import com.apjob.pojo.CandidateRecruitment;
import com.apjob.pojo.Employer;
import com.apjob.pojo.User;
import com.apjob.repository.CandidateRecruitmentRepository;
import com.apjob.repository.EmployerRepository;
import com.apjob.repository.RecruitmentNewsRepository;
import com.apjob.repository.UserRepository;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Transactional
@PropertySource("classpath:configs.properties")
@Repository
public class CandidateRecruitmentRepositoryImpl implements CandidateRecruitmentRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Autowired
    private Environment env;

    @Autowired
    private RecruitmentNewsRepository reRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmployerRepository emRepo;

    @Override
    public List<CandidateRecruitment> getCandidateRecruitments(int recruitmentId) {
        List<CandidateRecruitment> candidateRecruitments = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepo.getUserByEmail(authentication.getName());
        Employer e = this.emRepo.getEmployerById(u.getId());

        if (this.reRepo.getRecruitmentNewsById(recruitmentId).getEmployer().getId() == e.getId()) {
            Session s = this.factoryBean.getObject().getCurrentSession();
            Query q = s.createQuery("From CandidateRecruitment Where recruitment.id=:id");
            q.setParameter("id", recruitmentId);
            candidateRecruitments = q.getResultList();
        }

        return candidateRecruitments;
    }

    @Override
    public int countCandidateRecruitments(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        try {
            String candidateId = params.get("candidateId");
            String recruitmentId = params.get("recruitmentId");

            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<CandidateRecruitment> root = criteriaQuery.from(CandidateRecruitment.class);

            Predicate recruitmentPredicate = criteriaBuilder.equal(root.get("recruitment").get("id"), recruitmentId);
            Predicate candidatePredicate = criteriaBuilder.equal(root.get("candidate").get("id"), candidateId);

            Predicate combinedPredicate = criteriaBuilder.and(recruitmentPredicate, candidatePredicate);

            criteriaQuery.select(criteriaBuilder.count(root));
            criteriaQuery.where(combinedPredicate);

            Long result = s.createQuery(criteriaQuery).getSingleResult();

            return result != null ? result.intValue() : 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }

    }

    @Override
    public boolean addCandidateRecruitment(CandidateRecruitment c) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        try {
            s.save(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CandidateRecruitment> getCandidateRecruitmentById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        Query query = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CandidateRecruitment> criteriaQuery = criteriaBuilder.createQuery(CandidateRecruitment.class);
            Root root = criteriaQuery.from(CandidateRecruitment.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("recruitment").get("id"), "=" + id));

            query = session.createQuery(criteriaQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

    @Override
    public boolean deleteCandidateRecruitmentById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        List<CandidateRecruitment> candidateRecruitments = this.getCandidateRecruitmentById(id);
        try {
            for (CandidateRecruitment c : candidateRecruitments) {
                s.delete(c);
            }
            return candidateRecruitments.size() > 0;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
