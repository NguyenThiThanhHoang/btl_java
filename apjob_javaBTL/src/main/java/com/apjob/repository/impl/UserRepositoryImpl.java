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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Transactional
@PropertySource("classpath:configs.properties")
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

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

    @Override
    public User getUserByEmail(String email) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM User WHERE email=:email");
        q.setParameter("email", email);

        List<User> userList = q.getResultList();
        User u = new User();

        // Kiểm tra xem có người dùng nào có địa chỉ email tương ứng hay không
        if (userList.size() <= 0) {
            return u; // null nếu không tìm thấy
        } else {
            u = userList.get(0);
            return u;
        }

    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByEmail(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public boolean updateActive(int userId) {
        User u = this.getUserById(userId);
        if (u != null){
            u.setActive(Boolean.TRUE);
            this.addOrUpdateUser(u);
            return true;
        }
        return false;
    }

}
