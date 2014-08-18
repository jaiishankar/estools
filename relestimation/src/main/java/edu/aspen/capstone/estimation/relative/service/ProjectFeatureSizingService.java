
package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.ProjectFeatureSizingDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface ProjectFeatureSizingService {
    
    JSONResponseWrapper getAllByProject(Integer id);
    
    JSONResponseWrapper getAllByFeature(Integer id);
    
    JSONResponseWrapper get(Integer id);

    JSONResponseWrapper save(ProjectFeatureSizingDO data);

    JSONResponseWrapper delete(Integer id);
    
}
