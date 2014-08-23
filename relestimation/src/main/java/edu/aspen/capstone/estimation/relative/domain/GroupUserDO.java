
package edu.aspen.capstone.estimation.relative.domain;

import java.util.List;

/**
 *
 * @author jaiishankar
 */
public class GroupUserDO {
 
    private List<Integer> userIds;
    private Integer groupId;

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    
}
