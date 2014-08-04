/*
 *  
 *  
 * 
 */

package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.UserGroupDAO;
import edu.aspen.capstone.estimation.relative.domain.UserGroupDO;
import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import edu.aspen.capstone.estimation.relative.entity.UserGroup;
import edu.aspen.capstone.estimation.relative.utils.DOUtils;
import edu.aspen.capstone.estimation.relative.utils.JSONExceptionWrapper;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jaiishankar
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    UserGroupDAO userGroupDAO;
    
    @Override
    public JSONResponseWrapper getGroupsForUser(Integer usrId) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<UserGroup> userGroups = userGroupDAO.getAllByUser(usrId);
            UserGroupDO userGroup = new UserGroupDO();
            userGroup.setUserId(usrId);
            if (CollectionUtils.isNotEmpty(userGroups)) {
                userGroup = DOUtils.decodeUserGroup(userGroups);
            }
            return JSONResponseWrapper.getResponseInstance(userGroup);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper updateAssociations(UserGroupDO user) {
        try {
            List<UserGroup> currentList = userGroupDAO.getAllByUser(user.getUserId());
            List<UserGroup> reqList = DOUtils.encodeUserGroup(user);
            List<UserGroup> needsUpdate = new ArrayList<UserGroup>();
            List<UserGroup> needsDelete = new ArrayList<UserGroup>();
            //let us populate the needs insert&update list, we need to take the reqList 
            //and see if any not present in the current one.
            if (CollectionUtils.isNotEmpty(currentList)) {
                for (UserGroup grp : currentList) {
                    if (DOUtils.isGroupExistsForUserGroups(reqList, grp)) {
                        needsUpdate.add(grp);
                    } else {
                        needsDelete.add(grp);
                    }
                }

                for (UserGroup grp : reqList) {
                    if (!DOUtils.isGroupExistsForUserGroups(currentList, grp)) {
                        needsUpdate.add(grp);
                    }
                }
            } else {
                needsUpdate.addAll(reqList);
            }
            List<UserGroup> updated = null;
            boolean result = false;
            //Now update insert and update needed and delete the ones we don't
            //need.
            if (CollectionUtils.isNotEmpty(needsUpdate)) {
                updated = userGroupDAO.updateGroupsForUser(needsUpdate);
            }
            if (CollectionUtils.isNotEmpty(needsDelete)) {
                result = userGroupDAO.deleteGroupsForUser(needsDelete);
            }

            if (CollectionUtils.isNotEmpty(needsUpdate)
                    && CollectionUtils.isNotEmpty(updated)
                    && CollectionUtils.isNotEmpty(needsDelete) && result) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            } else if (CollectionUtils.isNotEmpty(needsUpdate)
                    && CollectionUtils.isNotEmpty(updated)
                    && CollectionUtils.isEmpty(needsDelete) && !result) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            } else if (CollectionUtils.isEmpty(needsUpdate)
                    && CollectionUtils.isEmpty(updated)
                    && CollectionUtils.isNotEmpty(needsDelete) && result) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(new JSONExceptionWrapper("Error", e));
        }
    }
    
}
