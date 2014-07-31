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
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id"),
    @NamedQuery(name = "Project.findByOwnerId", query = "SELECT p from Project p where p.ownerId = :ownerId"),
    @NamedQuery(name = "Project.findByTitle", query = "SELECT p FROM Project p WHERE p.title = :title"),
    @NamedQuery(name = "Project.findByDescription", query = "SELECT p FROM Project p WHERE p.description = :description")})
public class Project implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "ownerid")
    private Integer ownerId;

    @Size(max = 45)
    @Column(name = "title")
    private String title;
    
    @Size(max = 150)
    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy = "projectId")
    private Collection<ProjectMetric> projectMetricCollection;
    @OneToMany(mappedBy = "projectId")
    private Collection<ProjectContributors> projectContributorsCollection;
    @OneToMany(mappedBy = "projectId")
    private Collection<ProjectGroups> projectGroupsCollection;
    @OneToMany(mappedBy = "projectId")
    private Collection<ProjectFeatureSizing> projectFeatureSizingCollection;
    @OneToMany(mappedBy = "projectId")
    private Collection<Feature> featureCollection;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TS",
            nullable = false,
            updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TS", nullable = false)
    private Date updated;

    public Project() {
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Project(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProjectMetric> getProjectMetricCollection() {
        return projectMetricCollection;
    }

    public void setProjectMetricCollection(Collection<ProjectMetric> projectMetricCollection) {
        this.projectMetricCollection = projectMetricCollection;
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

    @XmlTransient
    @JsonIgnore
    public Collection<Feature> getFeatureCollection() {
        return featureCollection;
    }

    public void setFeatureCollection(Collection<Feature> featureCollection) {
        this.featureCollection = featureCollection;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.aspen.capstone.estimation.relative.entity.Project[ id=" + id + " ]";
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
