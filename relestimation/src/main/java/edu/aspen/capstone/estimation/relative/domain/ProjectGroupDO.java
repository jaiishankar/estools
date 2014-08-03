package edu.aspen.capstone.estimation.relative.domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public class ProjectGroupDO {

    private Integer id;
    private List<Integer> groupIds;
    private Integer projectId;
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

    public void setGroupIds(List<Integer> groupId) {
        this.groupIds = groupId;
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
    private Date updated;
    
    @Override
    public String toString(){
        return "[" + this.projectId + ","+ this.groupIds.toString()+"]";
    }
}
