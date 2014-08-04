/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.UserGroupDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface UserGroupService {

    JSONResponseWrapper getGroupsForUser(Integer usrId);

    JSONResponseWrapper updateAssociations(UserGroupDO project);
}
