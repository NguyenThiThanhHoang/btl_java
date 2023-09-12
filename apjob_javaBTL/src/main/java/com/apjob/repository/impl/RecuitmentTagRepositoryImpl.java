/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;
import com.apjob.pojo.RecruitmentTag;
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
import com.apjob.repository.RecruitmentTagRepository;

/**
 *
 * @author ASUS
 */
@Transactional
@PropertySource("classpath:configs.properties")
@Repository
public class RecuitmentTagRepositoryImpl implements RecruitmentTagRepository{
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<RecruitmentTag> getRecuitmentTags(Map<String, String> params) {
               Query query = null;
        try {
            String recruitmentId = params.get("recruitmentId");
            String tagId = params.get("tagId");

            Session session = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<RecruitmentTag> criteriaQuery = criteriaBuilder.createQuery(RecruitmentTag.class);
            Root root = criteriaQuery.from(RecruitmentTag.class);
            criteriaQuery.select(root);

            List<Predicate> predicates = new ArrayList<>();

            if (recruitmentId != null && !recruitmentId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("recruitment_id"), "=" + recruitmentId));
            }

            if (tagId != null && !tagId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("tag_id"), "=" + tagId));
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
    public int countRecuitmentTags(Map<String, String> params) {
         Session s = this.factoryBean.getObject().getCurrentSession();

        try {
            String recruitmentId = params.get("recruitmentId");
            String tagId = params.get("tagId");

            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<RecruitmentTag> root = criteriaQuery.from(RecruitmentTag.class);

            Predicate recruitmentPredicate = criteriaBuilder.equal(root.get("tag_id"), tagId);
            Predicate tagPredicate = criteriaBuilder.equal(root.get("recruitment_id"), recruitmentId);

            Predicate combinedPredicate = criteriaBuilder.and(recruitmentPredicate, tagPredicate);

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
    public boolean addRecuitmentTag(RecruitmentTag r) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        try {
            s.save(r);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<RecruitmentTag> getRecuitmentTagById(String recuitmentId, String tagId) {
        Session session = factoryBean.getObject().getCurrentSession();
        Query query = null;
        try {
           
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<RecruitmentTag> criteriaQuery = criteriaBuilder.createQuery(RecruitmentTag.class);
            Root root = criteriaQuery.from(RecruitmentTag.class);
            criteriaQuery.select(root);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("tag_id"), tagId));
            predicates.add(criteriaBuilder.equal(root.get("recruitment_id"), recuitmentId));
            criteriaQuery.where(predicates.toArray(new Predicate[0]));

            query = session.createQuery(criteriaQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

    @Override
    public boolean deleteRecuitmentTagById(String recuitmentId, String tagId) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        List<RecruitmentTag> recuitmentTags = this.getRecuitmentTagById(recuitmentId, tagId);
        try {
            for (RecruitmentTag c : recuitmentTags){
                 s.delete(c);
            }
            return recuitmentTags.size() > 0;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
