/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aspen.capstone.estimation.relative.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jaiishankar
 */
public interface AuditableBaseDomainObject extends Serializable {
   public void setId(Integer id);
    public Integer getId();
    
    public void setCreated(Date date);
    public Date getCreated();
    
    public void setUpdated(Date date);
    public Date getUpdated(); 
}
