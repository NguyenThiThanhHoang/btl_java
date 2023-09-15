/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;

import com.apjob.pojo.Rating;
import com.apjob.repository.RatingRepository;
import java.util.List;
import javax.persistence.Query;
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
    public List<Rating> getRatings(int comapnyId) {
        
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From Rating Where company.id=:id");
        q.setParameter("id", comapnyId);
        
        return q.getResultList();
        
    }

    @Override
    public int countRatings() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Rating");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Rating addOrUpdateRating(Rating r) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        try {
            if (r.getId() == null) {
                s.save(r);
            } else {
                s.update(r);
            }

            return r;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
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
