/*
 *  
 *  
 * 
 */

package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.BusinesscaseDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface BusinesscaseService {
    JSONResponseWrapper add(BusinesscaseDO bc);
    JSONResponseWrapper update(BusinesscaseDO bc);
    JSONResponseWrapper get(Integer id);
    JSONResponseWrapper delete(Integer id);
    JSONResponseWrapper getCasesForFeature(Integer fId);
}
