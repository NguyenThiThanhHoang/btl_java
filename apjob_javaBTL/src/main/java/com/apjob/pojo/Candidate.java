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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "candidate")
@NamedQueries({
    @NamedQuery(name = "Candidate.findAll", query = "SELECT c FROM Candidate c"),
    @NamedQuery(name = "Candidate.findById", query = "SELECT c FROM Candidate c WHERE c.id = :id"),
    @NamedQuery(name = "Candidate.findBySchoolName", query = "SELECT c FROM Candidate c WHERE c.schoolName = :schoolName"),
    @NamedQuery(name = "Candidate.findByBirthDay", query = "SELECT c FROM Candidate c WHERE c.birthDay = :birthDay")})
public class Candidate implements Serializable{

    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @NotNull(message = "{candidate.schoolName.notNullMsg}")
    @Size(min = 5, max = 50, message = "{candidate.schoolName.lenErrMsg}")

    @Column(name = "school_name")
    private String schoolName;
    
    @Column(name = "birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDay;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;
    
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    @JsonIgnore
    private Set<CV> cvSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @JsonIgnore
    private Set<CandidateRecruitment> candidateRecruitmentSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @JsonIgnore
    private Set<CandidateTag> candidateTags;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    @JsonIgnore
    private Set<Rating> ratingSet;
    
    
    
    public Candidate(){
        
    }
    
    public Candidate(Integer id) {
        this.id = id;
    }
    
    public Candidate(Integer id, String schoolName){
        this.id = id;
        this.schoolName = schoolName;
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
     * @return the schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName the schoolName to set
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @return the birthDay
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * @param birthDay the birthDay to set
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    
    /**
     * @return the userId
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the userId to set
     */
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * @return the cvSet
     */
    public Set<CV> getCvSet() {
        return cvSet;
    }

    /**
     * @param cvSet the cvSet to set
     */
    public void setCvSet(Set<CV> cvSet) {
        this.cvSet = cvSet;
    }


    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
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
        if (!(object instanceof Candidate)) {
            return false;
        }
        Candidate other = (Candidate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.Candidate[ id=" + id + " ]";
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
     * @return the ratingSet
     */
    public Set<Rating> getRatingSet() {
        return ratingSet;
    }

    /**
     * @param ratingSet the ratingSet to set
     */
    public void setRatingSet(Set<Rating> ratingSet) {
        this.ratingSet = ratingSet;
    }

}
