/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aspen.capstone.estimation.relative.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaiishankar
 */
@Entity
@Table(name = "applicationuser")
@NamedQueries({
    @NamedQuery(name = "ApplicationUser.findAll", query = "SELECT u FROM ApplicationUser u"),
    @NamedQuery(name = "ApplicationUser.findById", query = "SELECT u FROM ApplicationUser u WHERE u.id = :id"),
    @NamedQuery(name = "ApplicationUser.findByUname", query = "SELECT u FROM ApplicationUser u WHERE u.uname = :uname"),
    @NamedQuery(name = "ApplicationUser.findByPasscode", query = "SELECT u FROM ApplicationUser u WHERE u.passcode = :passcode"),
    @NamedQuery(name = "ApplicationUser.findByLname", query = "SELECT u FROM ApplicationUser u WHERE u.lname = :lname"),
    @NamedQuery(name = "ApplicationUser.findByFname", query = "SELECT u FROM ApplicationUser u WHERE u.fname = :fname"),
    @NamedQuery(name = "ApplicationUser.findByEmail", query = "SELECT u FROM ApplicationUser u WHERE u.email = :email"),
    @NamedQuery(name = "ApplicationUser.findByPhone", query = "SELECT u FROM ApplicationUser u WHERE u.phone = :phone"),
    @NamedQuery(name = "ApplicationUser.findByMname", query = "SELECT u FROM ApplicationUser u WHERE u.mname = :mname")})
public class ApplicationUser implements AuditableBaseDomainObject,Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "uname")
    private String uname;
    @Size(max = 45)
    @Column(name = "passcode")
    private String passcode;
    @Size(max = 45)
    @Column(name = "lname")
    private String lname;
    @Size(max = 45)
    @Column(name = "fname")
    private String fname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "phone")
    private String phone;
    @Size(max = 45)
    @Column(name = "mname")
    private String mname;
    @OneToMany(mappedBy = "userId")
    private Collection<UserGroup> userGroupCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<ProjectContributors> projectContributorsCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<ProjectFeatureSizing> projectFeatureSizingCollection;
    @Column(name = "LOGGED_IN")
    private Boolean isLoggedIn;
    @Column(name = "ACTIVE")
    private Boolean isActive;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TS",
            nullable = false,
            updatable = false, columnDefinition="DATETIME")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TS", nullable = false, columnDefinition="DATETIME")
    private Date updated;

    public ApplicationUser() {
    }

    public ApplicationUser(Integer id) {
        this.id = id;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date date) {
        this.created = date;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }

    @Override
    public void setUpdated(Date date) {
        this.updated = date;
    }
    @XmlTransient
    @JsonIgnore
    public Collection<UserGroup> getUserGroupCollection() {
        return userGroupCollection;
    }

    public void setUserGroupCollection(Collection<UserGroup> userGroupCollection) {
        this.userGroupCollection = userGroupCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProjectContributors> getProjectContributorsCollection() {
        return projectContributorsCollection;
    }

    public void setProjectContributorsCollection(Collection<ProjectContributors> projectContributorsCollection) {
        this.projectContributorsCollection = projectContributorsCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProjectFeatureSizing> getProjectFeatureSizingCollection() {
        return projectFeatureSizingCollection;
    }

    public void setProjectFeatureSizingCollection(Collection<ProjectFeatureSizing> projectFeatureSizingCollection) {
        this.projectFeatureSizingCollection = projectFeatureSizingCollection;
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
        if (!(object instanceof ApplicationUser)) {
            return false;
        }
        ApplicationUser other = (ApplicationUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id:"+this.id 
                + ",uname:" + this.uname 
                + ",pascode:"+this.passcode
                + ",created:"+this.created
                + ",update:" + this.updated
                ;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

}
