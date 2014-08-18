package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.FeatureDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface FeatureService {

    JSONResponseWrapper add(FeatureDO feature);

    JSONResponseWrapper update(FeatureDO feature);

    JSONResponseWrapper get(Integer id);

    JSONResponseWrapper delete(Integer id);

    JSONResponseWrapper getFeatursByProject(Integer projectId);

}
