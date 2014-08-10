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
@Table(name = "businesscase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Businesscase.findAll", query = "SELECT b FROM Businesscase b"),
    @NamedQuery(name = "Businesscase.findById", query = "SELECT b FROM Businesscase b WHERE b.id = :id"),
    @NamedQuery(name = "Businesscase.findByTaskDetails", query = "SELECT b FROM Businesscase b WHERE b.taskDetails = :taskDetails"),
    @NamedQuery(name = "Businesscase.findByNotes", query = "SELECT b FROM Businesscase b WHERE b.notes = :notes"),
    @NamedQuery(name = "Businesscase.findByQuestions", query = "SELECT b FROM Businesscase b WHERE b.questions = :questions"),
    @NamedQuery(name = "Businesscase.findByName", query = "SELECT b FROM Businesscase b WHERE b.name = :name"),
    @NamedQuery(name = "Businesscase.findByScoped", query = "SELECT b FROM Businesscase b WHERE b.scoped = :scoped")})
public class Businesscase implements AuditableBaseDomainObject, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "task_details")
    private String taskDetails;
    @Size(max = 500)
    @Column(name = "notes")
    private String notes;
    @Size(max = 500)
    @Column(name = "questions")
    private String questions;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 1)
    @Column(name = "scoped")
    private String scoped;
    @OneToMany(mappedBy = "section")
    private Collection<Assumption> assumptionCollection;
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    @ManyToOne
    private Priority priorityId;
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    @ManyToOne
    private Feature featureId;
    
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
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
    public Collection<Assumption> getAssumptionCollection() {
        return assumptionCollection;
    }

    public void setAssumptionCollection(Collection<Assumption> assumptionCollection) {
        this.assumptionCollection = assumptionCollection;
    }

    public Priority getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Priority priorityId) {
        this.priorityId = priorityId;
    }

    public Feature getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Feature featureId) {
        this.featureId = featureId;
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
