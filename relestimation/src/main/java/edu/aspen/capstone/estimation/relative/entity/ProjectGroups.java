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
 * Entity class for Project's associated groups
 * @author jaiishankar
 */
@Entity
@Table(name = "project_groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectGroups.findAll", query = "SELECT p FROM ProjectGroups p"),
    @NamedQuery(name = "ProjectGroups.findById", query = "SELECT p FROM ProjectGroups p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectGroups.findByProjectId", query = "SELECT p FROM ProjectGroups p WHERE p.projectId = :projectId"),
    @NamedQuery(name = "ProjectGroups.deleteByGroupAndProjectId", query = "DELETE FROM ProjectGroups p WHERE p.groupId = :groupId and p.projectId = :projectId")
})
public class ProjectGroups implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "group_id")
    private Integer groupId;
    
    @Column(name = "project_id")
    private Integer projectId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TS",
            nullable = false,
            updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TS", nullable = false)
    private Date updated;

    public ProjectGroups() {
    }

    public ProjectGroups(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
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

    @Override
    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof ProjectGroups)) {
            return false;
        }
        ProjectGroups other = (ProjectGroups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[id: "+this.id+", projectid: "+this.projectId+", groupid:"+this.groupId+"]";
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
