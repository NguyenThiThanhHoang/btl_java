/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.respository.impl;

import com.apjob.pojo.Candidate;
import com.apjob.repository.CandidateRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class CandidateRepositoryImpl implements CandidateRepository{
    
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<Candidate> getCandidates(Map<String, String> params) {
        Query query = null;
        try {
            String kw = params.get("kw");
            String email = null;
            String name = null;
            
            //dùng regex kiểm tra chuỗi truyền vào có phải email không -> email thì gán -> ngược lại là name
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(kw);
            if (matcher.matches()){
                email = kw;
            }else{ name = kw; }
            
            Session session = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
            Root root = criteriaQuery.from(Candidate.class);
            criteriaQuery.select(root);

            List<Predicate> predicates = new ArrayList<>();

            if (email != null && email.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("user").get("email"), "%" + email + "%"));
            }

            if (name != null && name.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("user").get("name"), "%" + name + "%"));
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
    public int countCandidates() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Candidate");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateCandidate(Candidate c) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        try {
            if (c.getId() == null) {
                s.save(c);
            } else {
                s.update(c);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Candidate getCandidateById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Candidate.class, id);
    }

    @Override
    public boolean deleteCandidate(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Candidate c = this.getCandidateById(id);
        try {
            s.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
