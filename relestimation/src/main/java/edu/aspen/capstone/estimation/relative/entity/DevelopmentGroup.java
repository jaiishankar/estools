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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaiishankar
 */
@Entity
@Table(name = "developmentgroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevelopmentGroup.findAll", query = "SELECT g FROM DevelopmentGroup g"),
    @NamedQuery(name = "DevelopmentGroup.findById", query = "SELECT g FROM DevelopmentGroup g WHERE g.id = :id"),
    @NamedQuery(name = "DevelopmentGroup.findByName", query = "SELECT g FROM DevelopmentGroup g WHERE g.name = :name")})
public class DevelopmentGroup implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "groupId")
    private Collection<UserGroup> userGroupCollection;
    @OneToMany(mappedBy = "groupId")
    private Collection<ProjectContributors> projectContributorsCollection;
    @OneToMany(mappedBy = "groupId")
    private Collection<ProjectGroups> projectGroupsCollection;
    @OneToMany(mappedBy = "groupId")
    private Collection<ProjectFeatureSizing> projectFeatureSizingCollection;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts",
            nullable = false,
            updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_ts", nullable = false)
    private Date updated;

    public DevelopmentGroup() {
    }

    public DevelopmentGroup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Collection<ProjectGroups> getProjectGroupsCollection() {
        return projectGroupsCollection;
    }

    public void setProjectGroupsCollection(Collection<ProjectGroups> projectGroupsCollection) {
        this.projectGroupsCollection = projectGroupsCollection;
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
        if (!(object instanceof DevelopmentGroup)) {
            return false;
        }
        DevelopmentGroup other = (DevelopmentGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ id:"+this.id+",name:"+this.name+"]";
    }

    @Override
    public void setCreated(Date date) {
        this.created = date;
    }

    @Override
    public Date getCreated() {
        return this.created;
    }

    @Override
    public void setUpdated(Date date) {
        this.updated = date;
    }

    @Override
    public Date getUpdated() {
        return this.updated;
    }

}
