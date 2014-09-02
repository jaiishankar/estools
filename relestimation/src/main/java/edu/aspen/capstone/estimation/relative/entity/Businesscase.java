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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaiishankar
 */
@Entity
@Table(name = "businesscase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Businesscase.findAll", query = "SELECT b FROM Businesscase b"),
    @NamedQuery(name = "Businesscase.findById", query = "SELECT b FROM Businesscase b WHERE b.id = :id"),
    @NamedQuery(name = "Businesscase.findByScoped", query = "SELECT b FROM Businesscase b WHERE b.scoped = :scoped"),
    @NamedQuery(name="Businesscase.deletebyFeature", query="DELETE FROM Businesscase b WHERE b.featureId = :featureId"),
    @NamedQuery(name = "Businesscase.findByFeature", query = "SELECT b FROM Businesscase b WHERE b.featureId = :featureId")
})
public class Businesscase implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "task_details")
    private String taskDetails;
    @Size(max = 500)
    @Column(name = "note")
    private String note;
    @Size(max = 500)
    @Column(name = "question")
    private String question;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 1)
    @Column(name = "scoped")
    private String scoped;
    @Column(name = "priority_id")
    private Integer priorityId;
    @Column(name = "feature_id")
    private Integer featureId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts",
            nullable = false,
            updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_ts", nullable = false)
    private Date updated;

    public Businesscase() {
    }

    public Businesscase(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String notes) {
        this.note = notes;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String questions) {
        this.question = questions;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Businesscase)) {
            return false;
        }
        Businesscase other = (Businesscase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.aspen.capstone.estimation.relative.entity.Businesscase[ id=" + id + " ]";
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

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

}
