/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aspen.capstone.estimation.relative.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "assumption")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assumption.findAll", query = "SELECT a FROM Assumption a"),
    @NamedQuery(name = "Assumption.findById", query = "SELECT a FROM Assumption a WHERE a.id = :id"),
    @NamedQuery(name = "Assumption.findByAssumption", query = "SELECT a FROM Assumption a WHERE a.assumption = :assumption")})
public class Assumption implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Size(max = 1500)
    @Column(name = "assumption")
    private String assumption;
    @JoinColumn(name = "section", referencedColumnName = "id")
    @ManyToOne
    private Businesscase section;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private AssumptionType type;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TS",
            nullable = false,
            updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TS", nullable = false)
    private Date updated;

    public Assumption() {
    }

    public Assumption(Integer id) {
        this.id = id;
    }

    public String getAssumption() {
        return assumption;
    }

    public void setAssumption(String assumption) {
        this.assumption = assumption;
    }

    public Businesscase getSection() {
        return section;
    }

    public void setSection(Businesscase section) {
        this.section = section;
    }

    public AssumptionType getType() {
        return type;
    }

    public void setType(AssumptionType type) {
        this.type = type;
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
        if (!(object instanceof Assumption)) {
            return false;
        }
        Assumption other = (Assumption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.aspen.capstone.estimation.relative.entity.Assumption[ id=" + id + " ]";
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
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
