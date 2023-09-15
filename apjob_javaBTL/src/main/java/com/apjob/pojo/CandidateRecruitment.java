/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Entity
@XmlRootElement
@Table(name = "candidate_recruitment")
@NamedQueries({
    @NamedQuery(name = "CandidateRecruitment.findAll", query = "SELECT c FROM CandidateRecruitment c"),
    @NamedQuery(name = "CandidateRecruitment.findByTextMail", query = "SELECT c FROM CandidateRecruitment c WHERE c.textMail = :textMail"),
    @NamedQuery(name = "CandidateRecruitment.findByCreatedDay", query = "SELECT c FROM CandidateRecruitment c WHERE c.createdDay = :createdDay")})
public class CandidateRecruitment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private Employer employer;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recruitment_id", referencedColumnName = "id")
    private RecruitmentNews recruitment;

    @Basic(optional = false)
    @NotNull(message = "{candidateRecruitment.textMail.notNullMsg}")
    @Size(min = 5, max = 1000, message = "{candidateRecruitment.textMail.lenErrMsg}")
    @Column(name = "text_mail")
    private String textMail;

    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDay;

    @Column(name = "link_cv")
    private String linkCV;

    public CandidateRecruitment() {

    }

    public CandidateRecruitment(Candidate candidate, Employer employer, RecruitmentNews recruitment) {
        this.candidate = candidate;
        this.employer = employer;
        this.recruitment = recruitment;
    }

    /**
     * @return the candidate
     */
    public Candidate getCandidate() {
        return candidate;
    }

    /**
     * @param candidate the candidate to set
     */
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
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

    /**
     * @return the recruitment
     */
    public RecruitmentNews getRecruitment() {
        return recruitment;
    }

    /**
     * @param recruitment the recruitment to set
     */
    public void setRecruitment(RecruitmentNews recruitment) {
        this.recruitment = recruitment;
    }

    /**
     * @return the textMail
     */
    public String getTextMail() {
        return textMail;
    }

    /**
     * @param textMail the textMail to set
     */
    public void setTextMail(String textMail) {
        this.textMail = textMail;
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

    @Override
    public int hashCode() {
        return Objects.hash(candidate, employer, recruitment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CandidateRecruitment other = (CandidateRecruitment) obj;
        return Objects.equals(candidate, other.candidate)
                && Objects.equals(employer, other.employer)
                && Objects.equals(recruitment, other.recruitment);
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.CandidateRecruitment[ candidate=" + candidate
                + ",  employer=" + employer + ", recruitment=" + recruitment + " ]";
    }

    /**
     * @return the linkCV
     */
    public String getLinkCV() {
        return linkCV;
    }

    /**
     * @param linkCV the linkCV to set
     */
    public void setLinkCV(String linkCV) {
        this.linkCV = linkCV;
    }

}
