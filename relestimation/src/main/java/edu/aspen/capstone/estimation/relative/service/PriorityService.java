package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.PriorityDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface PriorityService {

    JSONResponseWrapper listAll();

    JSONResponseWrapper getPriority(Integer id);

    JSONResponseWrapper savePriority(PriorityDO data);

    JSONResponseWrapper deletePriority(Integer id);

}
