package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.ProjectFeatureSizingDAO;
import edu.aspen.capstone.estimation.relative.domain.ProjectFeatureSizingDO;
import edu.aspen.capstone.estimation.relative.entity.ProjectFeatureSizing;
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
public class ProjectFeatureSizingServiceImpl implements ProjectFeatureSizingService {

    @Autowired
    ProjectFeatureSizingDAO dao;

    @Override
    public JSONResponseWrapper getAllByProject(Integer id) {
        try {
            return JSONResponseWrapper.getResponseInstance(this.convertIt(dao.getByProject(id)));
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper getAllByFeature(Integer id) {
        try {
            return JSONResponseWrapper.getResponseInstance(this.convertIt(dao.getByFeature(id)));
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper get(Integer id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ProjectFeatureSizing thismapping = dao.get(id);
            if(thismapping != null){
            ProjectFeatureSizingDO tempMapping = modelMapper.map(thismapping, ProjectFeatureSizingDO.class);
            return JSONResponseWrapper.getResponseInstance(tempMapping);
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper save(ProjectFeatureSizingDO data) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ProjectFeatureSizing mymappings = modelMapper.map(data, ProjectFeatureSizing.class);

            ProjectFeatureSizing tempCase = dao.save(mymappings);
            if (tempCase != null) {
                ProjectFeatureSizingDO response = modelMapper.map(tempCase, ProjectFeatureSizingDO.class);
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
    public JSONResponseWrapper delete(Integer id) {
        try {
            if (dao.delete(id)) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    private List<ProjectFeatureSizingDO> convertIt(List<ProjectFeatureSizing> cases) {
        ModelMapper modelMapper = new ModelMapper();
        ArrayList<ProjectFeatureSizingDO> featureCases = new ArrayList<ProjectFeatureSizingDO>();
        if (CollectionUtils.isNotEmpty(cases)) {
            for (ProjectFeatureSizing bcase : cases) {
                ProjectFeatureSizingDO tempFeature = modelMapper.map(bcase, ProjectFeatureSizingDO.class);
                featureCases.add(tempFeature);
            }
        }
        return featureCases;
    }

}
