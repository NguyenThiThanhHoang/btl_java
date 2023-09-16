/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;

import com.apjob.pojo.CV;
import com.apjob.pojo.Company;
import com.apjob.repository.CompanyRepository;
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
public class CompanyRepositoryImpl implements CompanyRepository{
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<Company> getCompanys(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Company> q = b.createQuery(Company.class);
        Root root = q.from(Company.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("nameCompany"), String.format("%%%s%%", kw)));
            }


            q.where(predicates.toArray(new Predicate[0]));
        }

        q.orderBy(b.desc(root.get("id")));

        Query query = s.createQuery(q);

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
    public int countCompanys() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Company");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateCompany(Company c) {
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
    public Company getCompanyById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Company.class, id);
    }

    @Override
    public boolean deleteCompany(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Company c = this.getCompanyById(id);
        try {
            s.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
   

    @Override
    public Company getCompanyByEmail(String email) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM Company WHERE email=:email");
        q.setParameter("email", email);

        List<Company> companys = q.getResultList();
        Company c = new Company();

        // Kiểm tra xem có người dùng nào có địa chỉ email tương ứng hay không
        if (companys.size() <= 0) {
            return c; // null nếu không tìm thấy
        } else {
            c = companys.get(0);
            return c;
        }
        
    }

    @Override
    public Company getCompanyByTax(int tax) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM Company WHERE tax=:tax");
        q.setParameter("tax", tax);

        List<Company> companys = q.getResultList();
        Company c = new Company();

        // Kiểm tra xem có người dùng nào có địa chỉ email tương ứng hay không
        if (companys.size() <= 0) {
            return c; // null nếu không tìm thấy
        } else {
            c = companys.get(0);
            return c;
        }
      
    }
}
