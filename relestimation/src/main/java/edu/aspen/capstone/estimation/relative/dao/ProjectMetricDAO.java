package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ProjectMetric;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface ProjectMetricDAO {

    List<ProjectMetric> getAllByProject(Integer prjId);

    List<ProjectMetric> saveorUpdateMetricsForProject(List<ProjectMetric> values);

    Boolean deleteMetricsForProject(List<ProjectMetric> values);
    
    Boolean deleteMetricsForProject(Integer projectId);
    
    ProjectMetric save(ProjectMetric data);
    
    Boolean delete(Integer id);
    
    ProjectMetric get(Integer id);

}
