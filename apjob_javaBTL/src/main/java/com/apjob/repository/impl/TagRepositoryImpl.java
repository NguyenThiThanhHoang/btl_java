/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.repository.impl;

import com.apjob.pojo.Tag;
import com.apjob.repository.TagRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class TagRepositoryImpl implements TagRepository{

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;
    
    @Override
    public List<Tag> getTags(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Tag> q = b.createQuery(Tag.class);
        Root root = q.from(Tag.class);
        q.select(root);

        if (params != null) {
           
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                q.where(b.like(root.get("tag_name"), String.format("%%%s%%", kw)));
            }
            
        }

        q.orderBy(b.desc(root.get("id")));

        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null) {
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE_SMALL_ITEM"));
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public int countTags() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Tag");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateTag(Tag t) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        try {
            if (t.getId() == null) {
                s.save(t);
            } else {
                s.update(t);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Tag getTagById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Tag.class, id);
    }

    @Override
    public boolean deleteTag(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Tag t = this.getTagById(id);
        try {
            s.delete(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
