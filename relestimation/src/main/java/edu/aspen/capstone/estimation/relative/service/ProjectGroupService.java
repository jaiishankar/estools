package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.ProjectGroupDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface ProjectGroupService {
    JSONResponseWrapper getGroupsForProject(Integer prjId);
    JSONResponseWrapper updateAssociations(ProjectGroupDO project);
}
