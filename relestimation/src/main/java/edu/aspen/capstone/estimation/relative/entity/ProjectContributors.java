package edu.aspen.capstone.estimation.relative.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity object for recording contributors for this project
 *
 * @author jaiishankar
 */
@Entity
@Table(name = "project_contributors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectContributors.findAll", query = "SELECT p FROM ProjectContributors p"),
    @NamedQuery(name = "ProjectContributors.findById", query = "SELECT p FROM ProjectContributors p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectContributors.findByUserId", query = "SELECT p FROM ProjectContributors p WHERE p.userId = :userId"),
    @NamedQuery(name = "ProjectContributors.findByGroupId", query = "SELECT p FROM ProjectContributors p WHERE p.groupId = :groupId"),
    @NamedQuery(name="ProjectContributors.deleteByProject", query="DELETE FROm ProjectContributors p WHERE p.projectId = :projectId"),
    @NamedQuery(name = "ProjectContributors.findByProjectId", query = "SELECT p FROM ProjectContributors p WHERE p.projectId = :projectId")
})
public class ProjectContributors implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "contributor_type")
    private String contributorType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts",
            nullable = false,
            updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_ts", nullable = false)
    private Date updated;

    public ProjectContributors() {
    }

    public ProjectContributors(Integer id) {
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getContributorType() {
        return contributorType;
    }

    public void setContributorType(String contributorType) {
        this.contributorType = contributorType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProjectContributors)) {
            return false;
        }
        ProjectContributors other = (ProjectContributors) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.aspen.capstone.estimation.relative.entity.ProjectContributors[ id=" + id + " ]";
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
