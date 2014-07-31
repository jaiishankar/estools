/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.utils;

import edu.aspen.capstone.estimation.relative.domain.ProjectGroupDO;
import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public class DOUtils {
    
    public static ProjectGroupDO decode(List<ProjectGroups> pgroups) {
        ProjectGroupDO result = new ProjectGroupDO();
        List<Integer> groups = new ArrayList<Integer>();
        for (ProjectGroups pgroup : pgroups) {
            result.setProjectId(pgroup.getProjectId());
            groups.add(pgroup.getGroupId());
        }
        result.setGroupIds(groups);
        return result;
    }
    
    
}
