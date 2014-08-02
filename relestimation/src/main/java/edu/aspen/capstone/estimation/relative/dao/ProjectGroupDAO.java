package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface ProjectGroupDAO {

    List<ProjectGroups> getAllByProject(Integer projectId);

    Boolean updateGroupsForProject(Integer projectId, List<ProjectGroups> groups);
}
