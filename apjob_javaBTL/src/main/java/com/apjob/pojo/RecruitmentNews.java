/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@XmlRootElement
@Table(name = "recruitment_news")
@NamedQueries({
    @NamedQuery(name = "RecruitmentNews.findAll", query = "SELECT r FROM RecruitmentNews r"),
    @NamedQuery(name = "RecruitmentNews.findById", query = "SELECT r FROM RecruitmentNews r WHERE r.id = :id"),
    @NamedQuery(name = "RecruitmentNews.findByTitle", query = "SELECT r FROM RecruitmentNews r WHERE r.title = :title"),
    @NamedQuery(name = "RecruitmentNews.findByJobVanacy", query = "SELECT r FROM RecruitmentNews r WHERE r.jobVanacy = :jobVanacy"),
    @NamedQuery(name = "RecruitmentNews.findBySalary", query = "SELECT r FROM RecruitmentNews r WHERE r.salary = :salary"),
    @NamedQuery(name = "RecruitmentNews.findByDescription", query = "SELECT r FROM RecruitmentNews r WHERE r.description = :description"),
    @NamedQuery(name = "RecruitmentNews.findByDeadline", query = "SELECT r FROM RecruitmentNews r WHERE r.deadline = :deadline"),
    @NamedQuery(name = "RecruitmentNews.findByCreatedDay", query = "SELECT r FROM RecruitmentNews r WHERE r.createdDay = :createdDay")})
public class RecruitmentNews implements Serializable{

   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "title")
    @NotNull(message = "{recruitment.name.notNullMsg}")
    @Size(min = 5, max = 50, message = "{recruitment.title.lenErrMsg}")
    private String title;
    
    @Column(name = "description")
    @Size(min=10, max = 255, message = "{recruitment.desc.lenErrMsg}")
    private String description;
    
    @Basic(optional = false)
    @Column(name = "job_vanacy")
    @Size(min=10, max = 50, message = "{recruitment.jobVanacy.lenErrMsg}")
    private String jobVanacy;
    
    @Basic(optional = false)
    @Column(name = "salary")
    @Size(min=10, max = 50, message = "{recruitment.salary.lenErrMsg}")
    private String salary;
    
    @Basic(optional = false)
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    
    @Basic(optional = false)
    @Column(name = "created_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDay;
    
   
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private Employer employer;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recruitment")
    @JsonIgnore
    private Set<CandidateRecruitment> candidateRecruitmentSet;
    
    
    public RecruitmentNews(){
        
    }
    
     public RecruitmentNews(Integer id) {
        this.id = id;
    }

    public RecruitmentNews(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    
     /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the jobVanacy
     */
    public String getJobVanacy() {
        return jobVanacy;
    }

    /**
     * @param jobVanacy the jobVanacy to set
     */
    public void setJobVanacy(String jobVanacy) {
        this.jobVanacy = jobVanacy;
    }

    /**
     * @return the salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**
     * @return the createdDay
     */
    public Date getCreatedDay() {
        return createdDay;
    }

    /**
     * @param createdDay the createdDay to set
     */
    public void setCreatedDay(Date createdDay) {
        this.createdDay = createdDay;
    }

    /**
     * @return the employer
     */
    public Employer getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(Employer employer) {
        this.employer = employer;
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
        if (!(object instanceof RecruitmentNews)) {
            return false;
        }
        RecruitmentNews other = (RecruitmentNews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.RecruitmentNews[ id=" + id + " ]";
    }

    /**
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

   
}
