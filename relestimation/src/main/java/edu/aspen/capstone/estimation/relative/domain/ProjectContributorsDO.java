
package edu.aspen.capstone.estimation.relative.domain;

import java.util.Date;

/**
 *
 * @author jaiishankar
 */
public class ProjectContributorsDO {
    
    private Integer id;

    private Integer userId;

    private Integer groupId;

    private Integer projectId;

    private String contributorType;

    private Date created;

    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getContributorType() {
        return contributorType;
    }

    public void setContributorType(String contributorType) {
        this.contributorType = contributorType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    
}
