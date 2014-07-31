package edu.aspen.capstone.estimation.relative.domain;

import java.util.Date;

/**
 *
 * @author jaiishankar
 */
public class DevelopmentGroupDO {

    private Integer id;
    private String name;
    private Date created;
    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "[ id:" + this.id + ",name:" + this.name + "]";
    }
}
