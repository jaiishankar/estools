package edu.aspen.capstone.estimation.relative.domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public class FeatureDO {

    private Integer id;

    private Integer projectId;

    private Integer type;

    private String title;

    private String task;

    private String name;

    private String scoped;

    private List<BusinesscaseDO> cases;

    private Date created;
    
    private Date updated;

}
