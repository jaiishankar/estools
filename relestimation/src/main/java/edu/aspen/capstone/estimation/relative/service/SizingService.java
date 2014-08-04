/*
 *  
 *  
 * 
 */

package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.SizingDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface SizingService {
    
    JSONResponseWrapper listAll();
    
    JSONResponseWrapper saveOrUpdate(SizingDO sizing);
    
    JSONResponseWrapper delete(Integer id);
    
}
