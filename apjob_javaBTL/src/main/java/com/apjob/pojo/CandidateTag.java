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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@XmlRootElement
@Table(name = "candidate_tag")
@NamedQueries({
    @NamedQuery(name = "CandidateTag.findAll", query = "SELECT c FROM CandidateTag c"),
    @NamedQuery(name = "CandidateTag.findByTagName", query = "SELECT c FROM CandidateTag c WHERE c.tagName = :tagName")})
public class CandidateTag implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
   
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tag tag;
    
    @Basic(optional = false)
    @Column(name = "tag_name")
    private String tagName;
    
    
    
    public CandidateTag(){
        
    }
    
    public CandidateTag(Candidate candidate, Tag tag, String tagName){
        this.candidate = candidate;
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
    
    @Override
    public int hashCode() {
        return Objects.hash(candidate, tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CandidateTag other = (CandidateTag) obj;
        return Objects.equals(candidate, other.candidate) &&
               Objects.equals(tag, other.tag);
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.CandidateRecruitment[ candidate=" + candidate 
                + ",  tag=" + tag + " ]";
    }
}
