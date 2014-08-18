package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.ProjectContributorsDAO;
import edu.aspen.capstone.estimation.relative.domain.ProjectContributorsDO;
import edu.aspen.capstone.estimation.relative.entity.ProjectContributors;
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
public class ProjectContributorsServiceImpl implements ProjectContributorsService {

    @Autowired
    ProjectContributorsDAO dao;

    @Override
    public JSONResponseWrapper getByUsers(Integer id) {
        try {
            return JSONResponseWrapper.getResponseInstance(this.convertIt(dao.getByUsers(id)));
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper getByProject(Integer id) {
        try {
            return JSONResponseWrapper.getResponseInstance(this.convertIt(dao.getByProject(id)));
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper getByGroup(Integer id) {
        try {
            return JSONResponseWrapper.getResponseInstance(this.convertIt(dao.getByGroup(id)));
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper get(Integer id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ProjectContributors thismapping = dao.get(id);
            if (thismapping != null) {
                ProjectContributorsDO tempMapping = modelMapper.map(thismapping, ProjectContributorsDO.class);
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
    public JSONResponseWrapper save(ProjectContributorsDO data) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ProjectContributors mymappings = modelMapper.map(data, ProjectContributors.class);

            ProjectContributors tempCase = dao.save(mymappings);
            if (tempCase != null) {
                ProjectContributorsDO response = modelMapper.map(tempCase, ProjectContributorsDO.class);
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

    private List<ProjectContributorsDO> convertIt(List<ProjectContributors> cases) {
        ModelMapper modelMapper = new ModelMapper();
        ArrayList<ProjectContributorsDO> featureCases = new ArrayList<ProjectContributorsDO>();
        if (CollectionUtils.isNotEmpty(cases)) {
            for (ProjectContributors bcase : cases) {
                ProjectContributorsDO tempFeature = modelMapper.map(bcase, ProjectContributorsDO.class);
                featureCases.add(tempFeature);
            }
        }
        return featureCases;
    }

}
