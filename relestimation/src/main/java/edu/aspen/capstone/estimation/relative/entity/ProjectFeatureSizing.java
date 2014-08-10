/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aspen.capstone.estimation.relative.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaiishankar
 */
@Entity
@Table(name = "project_feature_sizing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectFeatureSizing.findAll", query = "SELECT p FROM ProjectFeatureSizing p"),
    @NamedQuery(name = "ProjectFeatureSizing.findById", query = "SELECT p FROM ProjectFeatureSizing p WHERE p.id = :id")})
public class ProjectFeatureSizing implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "sizing_id", referencedColumnName = "id")
    @ManyToOne
    private Sizing sizingId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private ApplicationUser userId;
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @ManyToOne
    private DevelopmentGroup groupId;
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    @ManyToOne
    private Feature featureId;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne
    private Project projectId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts",
            nullable = false,
            updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_ts", nullable = false)
    private Date updated;

    public ProjectFeatureSizing() {
    }

    public ProjectFeatureSizing(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sizing getSizingId() {
        return sizingId;
    }

    public void setSizingId(Sizing sizingId) {
        this.sizingId = sizingId;
    }

    public ApplicationUser getUserId() {
        return userId;
    }

    public void setUserId(ApplicationUser userId) {
        this.userId = userId;
    }

    public DevelopmentGroup getGroupId() {
        return groupId;
    }

    public void setGroupId(DevelopmentGroup groupId) {
        this.groupId = groupId;
    }

    public Feature getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Feature featureId) {
        this.featureId = featureId;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
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
        if (!(object instanceof ProjectFeatureSizing)) {
            return false;
        }
        ProjectFeatureSizing other = (ProjectFeatureSizing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.aspen.capstone.estimation.relative.entity.ProjectFeatureSizing[ id=" + id + " ]";
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
