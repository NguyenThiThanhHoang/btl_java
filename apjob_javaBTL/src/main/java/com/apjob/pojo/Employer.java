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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@XmlRootElement
@Table(name = "employer")
@NamedQueries({
    @NamedQuery(name = "Employer.findAll", query = "SELECT e FROM Employer e"),
    @NamedQuery(name = "Employer.findById", query = "SELECT e FROM Employer e WHERE e.id = :id")})
public class Employer implements Serializable{

    
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "id")
    private Integer id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employer")
    @JsonIgnore
    private Set<CandidateRecruitment> candidateRecruitmentSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employer")
    @JsonIgnore
    private Set<RecruitmentNews> recruitmentNewsSet;
    
    public Employer(){
        
    }
    
    public Employer(Integer id){
        this.id = id;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employer)) {
            return false;
        }
        Employer other = (Employer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.Employer[ id=" + id + " ]";
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the candidateRecruitmentSet
     */
    public Set<CandidateRecruitment> getCandidateRecruitmentSet() {
        return candidateRecruitmentSet;
    }

    /**
     * @param candidateRecruitmentSet the candidateRecruitmentSet to set
     */
    public void setCandidateRecruitmentSet(Set<CandidateRecruitment> candidateRecruitmentSet) {
        this.candidateRecruitmentSet = candidateRecruitmentSet;
    }

    /**
     * @return the recruitmentNewsSet
     */
    public Set<RecruitmentNews> getRecruitmentNewsSet() {
        return recruitmentNewsSet;
    }

    /**
     * @param recruitmentNewsSet the recruitmentNewsSet to set
     */
    public void setRecruitmentNewsSet(Set<RecruitmentNews> recruitmentNewsSet) {
        this.recruitmentNewsSet = recruitmentNewsSet;
    }

    
}
    

