/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.pojo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@XmlRootElement
@Table(name = "company_tag")
@NamedQueries({
    @NamedQuery(name = "CompanyTag.findAll", query = "SELECT c FROM CompanyTag c"),
    @NamedQuery(name = "CompanyTag.findByTagName", query = "SELECT c FROM CompanyTag c WHERE c.tagName = :tagName")})
public class CompanyTag implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
   
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tag tag;
    
    @Basic(optional = false)
    @Column(name = "tag_name")
    private String tagName;
    
    public CompanyTag(){
        
    }
    
    public CompanyTag(Company company, Tag tag, String tagName){
        this.company = company;
        this.tag = tag;
        this.tagName = tagName;
    }
    
    /**
     * @return the tagName
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * @param tagName the tagName to set
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
  
    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(company, tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CompanyTag other = (CompanyTag) obj;
        return Objects.equals(company, other.company) &&
               Objects.equals(tag, other.tag);
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.CompanyTag[ company=" + company 
                + ",  tag=" + tag + " ]";
    }
    
}
