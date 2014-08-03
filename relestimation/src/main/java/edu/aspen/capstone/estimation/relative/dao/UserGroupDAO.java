package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.UserGroup;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface UserGroupDAO {
    
    List<UserGroup> getAllByProject(Integer userId);

    Boolean updateGroupsForUser(Integer userId, List<UserGroup> groups);
    
    Boolean deleteGroupsForUser(Integer userId);
}
