/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;

import com.apjob.pojo.CV;
import com.apjob.pojo.Rating;
import com.apjob.repository.CVRepository;
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
public class CVRepositoryImpl implements CVRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<CV> getCVs(int candidateId) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From Comment Where candidate.id=:id");
        q.setParameter("id", candidateId);
        
        return q.getResultList();
    }

    @Override
    public int countCVs() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM CV");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateCV(CV c) {
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
    public CV getCVById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(CV.class, id);
    }

    @Override
    public boolean deleteCV(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CV c = this.getCVById(id);
        try {
            s.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
