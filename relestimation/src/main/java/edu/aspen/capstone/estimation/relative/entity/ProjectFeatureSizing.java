package edu.aspen.capstone.estimation.relative.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class for Project-feature-sizing data
 * @author jaiishankar
 */
@Entity
@Table(name = "project_feature_sizing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectFeatureSizing.findAll", query = "SELECT p FROM ProjectFeatureSizing p"),
    @NamedQuery(name = "ProjectFeatureSizing.findById", query = "SELECT p FROM ProjectFeatureSizing p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectFeatureSizing.findByFeatureId", query = "SELECT p FROM ProjectFeatureSizing p WHERE p.featureId = :featureId"),
    @NamedQuery(name = "ProjectFeatureSizing.findByProjectId", query = "SELECT p FROM ProjectFeatureSizing p WHERE p.projectId = :projectId")
})
public class ProjectFeatureSizing implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "sizing_id")
    private Integer sizingId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "feature_id")
    private Integer featureId;

    @Column(name = "project_id")
    private Integer projectId;

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

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSizingId() {
        return sizingId;
    }

    public void setSizingId(Integer sizingId) {
        this.sizingId = sizingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
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
