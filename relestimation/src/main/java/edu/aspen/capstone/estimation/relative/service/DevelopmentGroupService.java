package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.DevelopmentGroupDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 * interface for all DevGroup related services
 * @author jaiishankar
 */
public interface DevelopmentGroupService {
    
    JSONResponseWrapper listAll();
    
    JSONResponseWrapper listGroup(Integer id);
    
    JSONResponseWrapper saveGroup(DevelopmentGroupDO grp);
    
    JSONResponseWrapper deleteGroup(Integer id);
    
}
