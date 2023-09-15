/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;

import com.apjob.pojo.CandidateTag;
import com.apjob.repository.CandidateTagRepository;
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
public class CandidateTagRepositoryImpl implements CandidateTagRepository{
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<CandidateTag> getCandidateTags(Map<String, String> params) {
        Query query = null;
        try {
            String candidateId = params.get("candidateId");
            String tagId = params.get("tagId");

            Session session = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CandidateTag> criteriaQuery = criteriaBuilder.createQuery(CandidateTag.class);
            Root root = criteriaQuery.from(CandidateTag.class);
            criteriaQuery.select(root);

            List<Predicate> predicates = new ArrayList<>();

            if (candidateId != null && !candidateId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("candidate_id"),  candidateId));
            }

            if (tagId != null && !tagId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("tag_id"),  tagId));
            }

            if (!predicates.isEmpty()) {
                criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            }

            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("tag_id")));
            query = session.createQuery(criteriaQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return query.getResultList();
    }

    @Override
    public int countCandidateTags(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        try {
            String candidateId = params.get("candidateId");
            String tagId = params.get("tagId");

            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<CandidateTag> root = criteriaQuery.from(CandidateTag.class);

            Predicate candidatePredicate = criteriaBuilder.equal(root.get("tag_id"), tagId);
            Predicate tagPredicate = criteriaBuilder.equal(root.get("candidate_id"), candidateId);

            Predicate combinedPredicate = criteriaBuilder.and(candidatePredicate, tagPredicate);

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
    public boolean addCandidateTag(CandidateTag c) {
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
    public List<CandidateTag> getCandidateTagById(String candidateId, String tagId ) {
        Session session = factoryBean.getObject().getCurrentSession();
        Query query = null;
        try {
           
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CandidateTag> criteriaQuery = criteriaBuilder.createQuery(CandidateTag.class);
            Root root = criteriaQuery.from(CandidateTag.class);
            criteriaQuery.select(root);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("tag_id"), tagId));
            predicates.add(criteriaBuilder.equal(root.get("candidate_id"), candidateId));
            criteriaQuery.where(predicates.toArray(new Predicate[0]));

            query = session.createQuery(criteriaQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

    @Override
    public boolean deleteCandidateTagById(String candidateId, String tagId) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        List<CandidateTag> candidateTags = this.getCandidateTagById(candidateId, tagId);
        try {
            for (CandidateTag c : candidateTags){
                 s.delete(c);
            }
            return candidateTags.size() > 0;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
