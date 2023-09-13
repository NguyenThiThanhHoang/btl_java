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
@Table(name = "recruitment_tag")
@NamedQueries({
    @NamedQuery(name = "RecruitmentTag.findAll", query = "SELECT r FROM RecruitmentTag r"),
    @NamedQuery(name = "RecruitmentTag.findByTagName", query = "SELECT r FROM RecruitmentTag r WHERE r.tagName = :tagName")})
public class RecruitmentTag implements Serializable{

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recruitment_id", referencedColumnName = "id")
    private RecruitmentNews recruitment;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tag tag;

    @Basic(optional = false)
    @Column(name = "tag_name")
    private String tagName;
    
    public RecruitmentTag(){
        
    }
    
    public RecruitmentTag(RecruitmentNews recruitment, Tag tag, String tagName){
        this.recruitment = recruitment;
        this.tag = tag;
        this.tagName = tagName;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(recruitment, tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        RecruitmentTag other = (RecruitmentTag) obj;
        return Objects.equals(recruitment, other.recruitment) &&
               Objects.equals(tag, other.tag);
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.RecuitmentTag[ company=" + recruitment 
                + ",  tag=" + tag + " ]";
    }
    
}
