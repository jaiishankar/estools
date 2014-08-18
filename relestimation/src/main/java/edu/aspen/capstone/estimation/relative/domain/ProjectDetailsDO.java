/**
 *  
 *  Contains all the data related to a single project.
 *  This object will be loaded with all the data that are
 *  needed to render the project on to the screen.
 * 
 */

package edu.aspen.capstone.estimation.relative.domain;

import java.util.List;

/**
 *
 * @author jaiishankar
 */
public class ProjectDetailsDO {
    
    private Integer projectId;
    
    private List<DevelopmentGroupDO> projectGroups;
    
    private List<ProjectMetricDO> projectMetrics;
    
    private List<FeatureDO> prjectFeatures;
    
}
