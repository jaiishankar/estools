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
@Table(name = "sizing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sizing.findAll", query = "SELECT s FROM Sizing s"),
    @NamedQuery(name = "Sizing.findById", query = "SELECT s FROM Sizing s WHERE s.id = :id"),
    @NamedQuery(name = "Sizing.findBySizeName", query = "SELECT s FROM Sizing s WHERE s.sizeValue = :name"),
    @NamedQuery(name = "Sizing.findBySizeValue", query = "SELECT s FROM Sizing s WHERE s.sizeValue = :value"),
    @NamedQuery(name = "Sizing.findByUom", query = "SELECT s FROM Sizing s WHERE s.uom = :uom")})
public class Sizing implements AuditableBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "sizename")
    private String sizeName;
    @Column(name = "sizevalue")
    private Integer sizeValue;
    @Column(name = "uom")
    private String uom;
    @OneToMany(mappedBy = "sizingId")
    private Collection<ProjectFeatureSizing> projectFeatureSizingCollection;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TS",
            nullable = false,
            updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TS", nullable = false)
    private Date updated;

    public Sizing() {
    }

    public Sizing(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Integer getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(Integer sizeValue) {
        this.sizeValue = sizeValue;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
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
        if (!(object instanceof Sizing)) {
            return false;
        }
        Sizing other = (Sizing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.aspen.capstone.estimation.relative.entity.Sizing[ id=" + id + " ]";
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
