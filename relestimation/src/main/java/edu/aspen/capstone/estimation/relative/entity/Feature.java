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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaiishankar
 */
@Entity
@Table(name = "feature")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feature.findAll", query = "SELECT f FROM Feature f"),
    @NamedQuery(name = "Feature.findById", query = "SELECT f FROM Feature f WHERE f.id = :id"),
    @NamedQuery(name = "Feature.findByType", query = "SELECT f FROM Feature f WHERE f.type = :type"),
    @NamedQuery(name = "Feature.findByTask", query = "SELECT f FROM Feature f WHERE f.task = :task"),
    @NamedQuery(name = "Feature.findByName", query = "SELECT f FROM Feature f WHERE f.name = :name"),
    @NamedQuery(name = "Feature.findByScoped", query = "SELECT f FROM Feature f WHERE f.scoped = :scoped")})
public class Feature implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "type")
    private Integer type;
    @Size(max = 200)
    @Column(name = "task")
    private String task;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 1)
    @Column(name = "scoped")
    private String scoped;
    @OneToMany(mappedBy = "featureId")
    private Collection<ProjectFeatureSizing> projectFeatureSizingCollection;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne
    private Project projectId;
    @OneToMany(mappedBy = "featureId")
    private Collection<Businesscase> businesscaseCollection;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts",
            nullable = false,
            updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_ts", nullable = false)
    private Date updated;

    public Feature() {
    }

    public Feature(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScoped() {
        return scoped;
    }

    public void setScoped(String scoped) {
        this.scoped = scoped;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProjectFeatureSizing> getProjectFeatureSizingCollection() {
        return projectFeatureSizingCollection;
    }

    public void setProjectFeatureSizingCollection(Collection<ProjectFeatureSizing> projectFeatureSizingCollection) {
        this.projectFeatureSizingCollection = projectFeatureSizingCollection;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Businesscase> getBusinesscaseCollection() {
        return businesscaseCollection;
    }

    public void setBusinesscaseCollection(Collection<Businesscase> businesscaseCollection) {
        this.businesscaseCollection = businesscaseCollection;
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
        if (!(object instanceof Feature)) {
            return false;
        }
        Feature other = (Feature) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.aspen.capstone.estimation.relative.entity.Feature[ id=" + id + " ]";
    }

    @Override
    public void setCreated(Date date) {
        this.created=date;
    }

    @Override
    public Date getCreated() {
        return this.created;
    }

    @Override
    public void setUpdated(Date date) {
        this.updated=date;
    }

    @Override
    public Date getUpdated() {
        return this.updated;
    }
}
