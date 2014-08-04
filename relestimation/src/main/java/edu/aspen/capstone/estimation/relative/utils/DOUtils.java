/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.utils;

import edu.aspen.capstone.estimation.relative.domain.ProjectGroupDO;
import edu.aspen.capstone.estimation.relative.domain.UserGroupDO;
import edu.aspen.capstone.estimation.relative.entity.AuditableBaseDomainObject;
import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import edu.aspen.capstone.estimation.relative.entity.UserGroup;
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
     * @param ugroups
     * @return
     */
    public static UserGroupDO decodeUserGroup(List<UserGroup> ugroups) {
        UserGroupDO result = new UserGroupDO();
        List<Integer> groups = new ArrayList<Integer>();
        for (UserGroup ugroup : ugroups) {
            result.setUserId(ugroup.getUserId());
            groups.add(ugroup.getGroupId());
        }
        result.setGroupIds(groups);
        return result;
    }

    /**
     *
     * @param pgroups
     * @return
     */
    public static ProjectGroupDO decodeProjectGroup(List<ProjectGroups> pgroups) {
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
    public static List<UserGroup> encodeUserGroup(UserGroupDO req) {
        List<UserGroup> groups = new ArrayList<UserGroup>();
        if (req != null && req.getGroupIds().size() > 0) {
            for (Integer grpId : req.getGroupIds()) {
                UserGroup tempgrp = new UserGroup();
                tempgrp.setUserId(req.getUserId());
                tempgrp.setGroupId(grpId);
                groups.add(tempgrp);
            }
        }
        return groups;
    }

    /**
     *
     * @param req
     * @return
     */
    public static List<ProjectGroups> encodeProjectGroup(ProjectGroupDO req) {
        List<ProjectGroups> groups = new ArrayList<ProjectGroups>();
        if (req != null && req.getGroupIds().size() > 0) {
            for (Integer grpId : req.getGroupIds()) {
                ProjectGroups tempgrp = new ProjectGroups();
                tempgrp.setProjectId(req.getProjectId());
                tempgrp.setGroupId(grpId);
                groups.add(tempgrp);
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
    public static boolean isGroupExistsForProjectGroups(List<ProjectGroups> list, ProjectGroups record) {
        boolean result = false;
        if (CollectionUtils.isNotEmpty(list) && record != null) {
            for (ProjectGroups grp : list) {
                if ((record.getProjectId() == grp.getProjectId())
                        && (record.getGroupId() == grp.getGroupId())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     *
     * @param list
     * @param record
     * @return
     */
    public static boolean isGroupExistsForUserGroups(List<UserGroup> list, UserGroup record) {
        boolean result = false;
        if (CollectionUtils.isNotEmpty(list) && record != null) {
            for (UserGroup ugrp : list) {
                if ((record.getUserId() == ugrp.getUserId())
                        && (record.getGroupId() == ugrp.getGroupId())) {
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
    public static List<? extends AuditableBaseDomainObject> mergeAndRemoveDuplicatesForProjects(List<? extends AuditableBaseDomainObject> list1, List<? extends AuditableBaseDomainObject> list2) {
        List<? extends AuditableBaseDomainObject> merged = null;
        if (CollectionUtils.isNotEmpty(list1)) {
            Set<AuditableBaseDomainObject> set = new HashSet<AuditableBaseDomainObject>(list1);
            if (CollectionUtils.isNotEmpty(list2)) {
                set.addAll(list2);
            }
            return new ArrayList<AuditableBaseDomainObject>(set);
        }

        if (CollectionUtils.isNotEmpty(list2)) {
            Set<AuditableBaseDomainObject> set = new HashSet<AuditableBaseDomainObject>(list2);
            if (CollectionUtils.isNotEmpty(list1)) {
                set.addAll(list1);
            }
            return new ArrayList<AuditableBaseDomainObject>(set);
        }

        return merged;
    }
}
