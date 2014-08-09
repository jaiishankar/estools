/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.ProjectMetricDAO;
import edu.aspen.capstone.estimation.relative.domain.ProjectMetricDO;
import edu.aspen.capstone.estimation.relative.entity.ProjectMetric;
import edu.aspen.capstone.estimation.relative.utils.JSONExceptionWrapper;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jaiishankar
 */
@Service
public class ProjectMetricServiceImpl implements ProjectMetricService {

    @Autowired
    ProjectMetricDAO projectmetricDAO;

    @Override
    public JSONResponseWrapper getMetricsForProject(Integer prjId) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<ProjectMetric> metrics = projectmetricDAO.getAllByProject(prjId);
            List<ProjectMetricDO> projectMetrics = new ArrayList<ProjectMetricDO>();
            if (CollectionUtils.isNotEmpty(metrics)) {
                for (ProjectMetric mtrc : metrics) {
                    ProjectMetricDO temp = modelMapper.map(metrics, ProjectMetricDO.class);
                    projectMetrics.add(temp);
                }
            }
            return JSONResponseWrapper.getResponseInstance(projectMetrics);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper save(ProjectMetricDO metric) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ProjectMetric mysize = modelMapper.map(metric, ProjectMetric.class);

            ProjectMetric tempSiz = projectmetricDAO.save(mysize);
            ProjectMetricDO response = modelMapper.map(tempSiz, ProjectMetricDO.class);
            if (tempSiz != null) {

                return (response != null)
                        ? JSONResponseWrapper.getResponseInstance(response)
                        : JSONResponseWrapper.getDefaultFailResponseInstance();
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper delete(Integer metricId) {
        try {
            if (projectmetricDAO.delete(metricId)) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper get(Integer metricId) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ProjectMetric p = projectmetricDAO.get(metricId);
            if (p != null) {
                ProjectMetricDO response = modelMapper.map(p, ProjectMetricDO.class);
                return JSONResponseWrapper.getResponseInstance(response);
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

}
