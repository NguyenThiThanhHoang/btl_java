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
@Table(name = "company")
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.nameCompany = :nameCompany"),
    @NamedQuery(name = "Company.findByDescription", query = "SELECT c FROM Company c WHERE c.description = :description"),
    @NamedQuery(name = "Company.findByTaxCode", query = "SELECT c FROM Company c WHERE c.tax = :tax"),
    @NamedQuery(name = "Company.findByEmail", query = "SELECT c FROM Company c WHERE c.emailCompany = :emailCompany"),
    @NamedQuery(name = "Company.findByPhone", query = "SELECT c FROM Company c WHERE c.phoneCompany = :phoneCompany"),
    @NamedQuery(name = "Company.findByAddress", query = "SELECT c FROM Company c WHERE c.address = :address")})
public class Company implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 5, max = 50, message = "{company.name.lenErrMsg}")
    private String nameCompany;
    
    
    @Column(name = "description")
    @Size(min=10, max = 255, message = "{company.desc.lenErrMsg}")
    private String description;
    
    @Basic(optional = false)
    @Column(name = "tax_code")
    private Integer tax;
    
   
    @Column(name = "email")
    private String emailCompany;
   
    @Column(name = "phone")
    private String phoneCompany;
    
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    
 
    @Column(name = "avatar")
    private String avatar;
    @Transient
    private MultipartFile fileCompany;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @JsonIgnore
    private Set<CompanyTag> companyTags;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @JsonIgnore
    private Set<Rating> ratingSet;
    
    public Company(){
        
    }
    
     public Company(Integer id) {
        this.id = id;
    }

    public Company(Integer id, String name) {
        this.id = id;
        this.nameCompany = name;
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
    public String getNameCompany() {
        return nameCompany;
    }

    /**
     * @param nameCompany the name to set
     */
    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
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
     * @return the tax
     */
    public Integer getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(Integer tax) {
        this.tax = tax;
    }

    /**
     * @return the email
     */
    public String getEmailCompany() {
        return emailCompany;
    }

    /**
     * @param emailCompany the email to set
     */
    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    /**
     * @return the phone
     */
    public String getPhoneCompany() {
        return phoneCompany;
    }

    /**
     * @param phoneCompany the phone to set
     */
    public void setPhoneCompany(String phoneCompany) {
        this.phoneCompany = phoneCompany;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the file
     */
    public MultipartFile getFileCompany() {
        return fileCompany;
    }

    /**
     * @param fileCompany the file to set
     */
    public void setFileCompany(MultipartFile fileCompany) {
        this.fileCompany = fileCompany;
    }

    /**
     * @return the employerSet
     */
  
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.Company[ id=" + id + " ]";
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

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

   

