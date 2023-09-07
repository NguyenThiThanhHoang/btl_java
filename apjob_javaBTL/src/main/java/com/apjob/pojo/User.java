/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.pojo;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByUserRole", query = "SELECT u FROM User u WHERE u.userRole = :userRole"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active")})
public class User implements Serializable{

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
     * @return the nameCompany
     */
    public String getNameCompany() {
        return nameCompany;
    }

    /**
     * @param nameCompany the nameCompany to set
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
     * @return the emailCompany
     */
    public String getEmailCompany() {
        return emailCompany;
    }

    /**
     * @param emailCompany the emailCompany to set
     */
    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    /**
     * @return the phoneCompany
     */
    public String getPhoneCompany() {
        return phoneCompany;
    }

    /**
     * @param phoneCompany the phoneCompany to set
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
     * @return the avatarCompany
     */
    public String getAvatarCompany() {
        return avatarCompany;
    }

    /**
     * @param avatarCompany the avatarCompany to set
     */
    public void setAvatarCompany(String avatarCompany) {
        this.avatarCompany = avatarCompany;
    }

    /**
     * @return the fileCompany
     */
    public MultipartFile getFileCompany() {
        return fileCompany;
    }

    /**
     * @param fileCompany the fileCompany to set
     */
    public void setFileCompany(MultipartFile fileCompany) {
        this.fileCompany = fileCompany;
    }

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
   
    @Basic(optional = false)
    @NotNull(message = "{user.name.notNullMsg}")
    @Size(min = 5, max = 100, message = "{user.name.lenErrMsg}")
    @Column(name = "name")
    private String name;
   
    @Basic(optional = false)
    @NotNull(message = "{user.email.notNullMsg}")
    @Size(min = 5, max = 50, message = "{user.email.lenErrMsg}")
    @Column(name = "email")
    private String email;
    
    @Basic(optional = false)
    @NotNull(message = "{user.phone.notNullMsg}")
    @Column(name = "phone")
    private String phone;
    
    @Basic(optional = false)
    @NotNull(message = "{user.password.notNullMsg}")
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private Boolean active;
    @Basic(optional = false)
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "avatar")
    private String avatar;
    @Transient
    private MultipartFile file;
    
    //Candidate
    @Transient
    private String schoolName;
    @Transient
    private Date birthDay;
    @Transient
    private Integer locationId;
   
    //employer - company
    @Transient
    private String nameCompany;
    @Transient
    private String description;
    @Transient
    private Integer tax;
    @Transient
    private String emailCompany;
    @Transient
    private String phoneCompany;
    @Transient
    private String address;
    @Transient
    private String avatarCompany;
    
    @Transient
    private MultipartFile fileCompany;
    

   
    public User(){
        
    }
    
     public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String email, String phone, String password, String userRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userRole = userRole;
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
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apjob.pojo.User[ id=" + id + " ]";
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

    /**
     * @return the location
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the location to set
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}
