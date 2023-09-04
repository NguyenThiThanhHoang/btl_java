/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.respository.impl;

import com.apjob.pojo.Rating;
import com.apjob.repository.RatingRepository;
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
@Transactional
@PropertySource("classpath:configs.properties")
@Repository
public class RatingRepositoryImpl implements RatingRepository{
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<Rating> getRatings(Map<String, String> params) {
        Query query = null;
        try {
            String candidateId = params.get("candidateId");
            String companyId = params.get("companyId");
            
            
            Session session = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Rating> criteriaQuery = criteriaBuilder.createQuery(Rating.class);
            Root root = criteriaQuery.from(Rating.class);
            criteriaQuery.select(root);

            List<Predicate> predicates = new ArrayList<>();

            if (candidateId != null && !candidateId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("candidate_id"), "=" + candidateId));
            }

            if (companyId != null && !companyId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("company_id"), "=" + companyId));
            }

            if (!predicates.isEmpty()) {
                criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            }

            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
            query = session.createQuery(criteriaQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    public int countRatings() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Rating");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateRating(Rating r) {
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
    public Rating getRatingById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Rating.class, id);
    }

    @Override
    public boolean deleteRating(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Rating r = this.getRatingById(id);
        try {
            s.delete(r);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
