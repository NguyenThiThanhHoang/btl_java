/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.pojo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Entity
@XmlRootElement
@Table(name = "cv")
@NamedQueries({
    @NamedQuery(name = "CV.findAll", query = "SELECT c FROM CV c"),
    @NamedQuery(name = "CV.findById", query = "SELECT c FROM CV c WHERE c.id = :id"),
    @NamedQuery(name = "CV.findByName", query = "SELECT c FROM CV c WHERE c.nameCV = :nameCV")})
public class CV implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name_cv")
    private String nameCV;

    @Column(name = "link_cv")
    private String linkCV;
    @Transient
    private MultipartFile file;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;

    public CV() {

    }

    public CV(Integer id) {
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
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CV)) {
            return false;
        }
        CV other = (CV) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.CV[ id=" + id + " ]";
    }

    /**
     * @return the nameCV
     */
    public String getNameCV() {
        return nameCV;
    }

    /**
     * @param nameCV the nameCV to set
     */
    public void setNameCV(String nameCV) {
        this.nameCV = nameCV;
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
