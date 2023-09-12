/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findById", query = "SELECT t FROM Tag t WHERE t.id = :id"),
    @NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = :name")})
public class Tag implements Serializable{

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tag_name")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tag")
    @JsonIgnore
    private Set<CandidateTag> candidateTags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tag")
    @JsonIgnore
    private Set<CompanyTag> companyTags;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tag")
    @JsonIgnore
    private Set<RecruitmentTag> recuitmentTags;
    
    public Tag() {
    }

    public Tag(Integer id) {
        this.id = id;
    }

    public Tag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * @return the candidateTags
     */
    public Set<CandidateTag> getCandidateTags() {
        return candidateTags;
    }

    /**
     * @param candidateTags the candidateTags to set
     */
    public void setCandidateTags(Set<CandidateTag> candidateTags) {
        this.candidateTags = candidateTags;
    }

    /**
     * @return the companyTags
     */
    public Set<CompanyTag> getCompanyTags() {
        return companyTags;
    }

    /**
     * @param companyTags the companyTags to set
     */
    public void setCompanyTags(Set<CompanyTag> companyTags) {
        this.companyTags = companyTags;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.Tag[ id=" + id + " ]";
    }

    /**
     * @return the recuitmentTags
     */
    public Set<RecruitmentTag> getRecuitmentTags() {
        return recuitmentTags;
    }

    /**
     * @param recuitmentTags the recuitmentTags to set
     */
    public void setRecuitmentTags(Set<RecruitmentTag> recuitmentTags) {
        this.recuitmentTags = recuitmentTags;
    }
}
