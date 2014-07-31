/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aspen.capstone.estimation.relative.utils;

import edu.aspen.capstone.estimation.relative.entity.AuditableBaseDomainObject;
import java.util.Date;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

/**
 *
 * @author jaiishankar
 */
public class CommonSaveOrUpdateDateListener extends DefaultSaveOrUpdateEventListener {
    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent event) {
        if (event.getObject() instanceof AuditableBaseDomainObject) {
            AuditableBaseDomainObject record = (AuditableBaseDomainObject) event.getObject();
            // set the Updated date/time
            record.setUpdated(new Date());
            // set the Created date/time
            if (record.getId() == null) {
                record.setCreated(new Date());
            }
            
            super.onSaveOrUpdate(event);
        }
    }
    
}
