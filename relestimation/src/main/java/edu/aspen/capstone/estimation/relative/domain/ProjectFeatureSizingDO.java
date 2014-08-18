package edu.aspen.capstone.estimation.relative.domain;

import java.util.Date;

/**
 * 
 * @author jaiishankar
 */
public class ProjectFeatureSizingDO {
    
    
    private Integer id;

    private Integer sizingId;

    private Integer userId;

    private Integer groupId;

    private Integer featureId;

    private Integer projectId;

    private Date created;

    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSizingId() {
        return sizingId;
    }

    public void setSizingId(Integer sizingId) {
        this.sizingId = sizingId;
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

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
