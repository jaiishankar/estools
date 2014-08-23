package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.UserGroup;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface UserGroupDAO {
    
    List<UserGroup> getAllByUser(Integer userId);
    
    List<UserGroup> getAllByGroup(Integer groupId);

    List<UserGroup> updateGroupsForUser(List<UserGroup> groups);
    
    Boolean deleteGroupsForUser(List<UserGroup> groups);
}
