package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface ProjectGroupDAO {

    List<ProjectGroups> getAllByProject(Integer projectId);

    List<ProjectGroups> updateGroupsForProject(List<ProjectGroups> groups);
    
    Boolean deleteGroupsForProject(List<ProjectGroups> groups);
    
    Boolean deleteAllByProject(Integer projectId);
}
