package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.AssumptionTypeDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface AssumptionTypeService {
    
    JSONResponseWrapper getAll();
    
    JSONResponseWrapper get(Integer id);
    
    JSONResponseWrapper save(AssumptionTypeDO asptyp);
    
    JSONResponseWrapper delete(Integer id);
    
    
}
