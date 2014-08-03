/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public class UserGroupDO {

    private Integer id;
    private List<Integer> groupIds;
    private Integer userId;
    private Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Integer> groupIds) {
        this.groupIds = groupIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
