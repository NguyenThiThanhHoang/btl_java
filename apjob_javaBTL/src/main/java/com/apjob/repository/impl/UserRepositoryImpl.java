/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;

import com.apjob.pojo.User;
import com.apjob.repository.UserRepository;
import java.util.List;
import javax.persistence.Query;
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
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    
    
    @Override
    public int countUsers() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM User");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateUser(User u) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        try {
            if (u.getId() == null) {
                s.save(u);
            } else {
                s.update(u);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(User.class, id);
    }

    @Override
    public boolean deleteUser(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        User u = this.getUserById(id);
        try {
            s.delete(u);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getUsers() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From User");
        return q.getResultList();
    }
    
}
