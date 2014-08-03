/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.utils;

import edu.aspen.capstone.estimation.relative.domain.ProjectGroupDO;
import edu.aspen.capstone.estimation.relative.entity.Project;
import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author jaiishankar
 */
public class DOUtils {
    /**
     * 
     * @param pgroups
     * @return 
     */
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
    
    /**
     * 
     * @param req
     * @return 
     */
    public static List<ProjectGroups> encode(ProjectGroupDO req) {
        List<ProjectGroups> groups = new ArrayList<ProjectGroups>();
        if (req != null && req.getGroupIds().size() > 0) {
            for (Integer grpId : req.getGroupIds()) {
                ProjectGroups tempgrp = new ProjectGroups();
                tempgrp.setProjectId(req.getProjectId());
                tempgrp.setGroupId(grpId);
            }
        }
        return groups;
    }
    
    /**
     * 
     * @param list
     * @param record
     * @return 
     */
    public static boolean isGroupExists(List<ProjectGroups> list, ProjectGroups record){
        boolean result = false;
        if(CollectionUtils.isNotEmpty(list) && record != null){
            for(ProjectGroups grp : list){
                if(record.equals(grp)){
                    result = true;
                    break;
                }
            }
        }
        
        return result;
    }
    
    /**
     * 
     * @param list1
     * @param list2
     * @return 
     */
    public static List<Project> mergeAndRemoveDuplicatesForProjects(List<Project> list1, List<Project> list2) {
        List<Project> merged = null;
        if (CollectionUtils.isNotEmpty(list1)) {
            Set<Project> set = new HashSet<Project>(list1);
            if (CollectionUtils.isNotEmpty(list2)) {
                set.addAll(list2);
            }
            return new ArrayList<Project>(set);
        }        
        
        if (CollectionUtils.isNotEmpty(list2)) {
            Set<Project> set = new HashSet<Project>(list2);
            if (CollectionUtils.isNotEmpty(list1)) {
                set.addAll(list1);
            }
            return new ArrayList<Project>(set);
        }        
        
        return merged;
    }
}
