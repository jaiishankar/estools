package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface ProjectGroupService {
    JSONResponseWrapper getGroupsForProject(Integer prjId);
    JSONResponseWrapper addAssociations(Integer projectId, Integer[] groupIds);
    JSONResponseWrapper removeAssociations(Integer projectId, Integer[] groupIds);
}
