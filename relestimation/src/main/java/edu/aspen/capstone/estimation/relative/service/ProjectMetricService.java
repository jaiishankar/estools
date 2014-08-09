package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.ProjectMetricDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface ProjectMetricService {

    JSONResponseWrapper getMetricsForProject(Integer prjId);

    JSONResponseWrapper save(ProjectMetricDO metric);

    JSONResponseWrapper delete(Integer metricId);

    JSONResponseWrapper get(Integer metricId);
}
