package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.BusinesscaseDAO;
import edu.aspen.capstone.estimation.relative.dao.FeatureDAO;
import edu.aspen.capstone.estimation.relative.domain.FeatureDO;
import edu.aspen.capstone.estimation.relative.entity.Feature;
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
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    FeatureDAO featureDAO;
    @Autowired
    BusinesscaseDAO caseDAO;

    @Override
    public JSONResponseWrapper add(FeatureDO feature) {
        return this.update(feature);
    }

    @Override
    public JSONResponseWrapper update(FeatureDO feature) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Feature myasptyp = modelMapper.map(feature, Feature.class);

            Feature tempFeature = featureDAO.update(myasptyp);
            if (tempFeature != null) {
                FeatureDO response = modelMapper.map(tempFeature, FeatureDO.class);
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
    public JSONResponseWrapper get(Integer id) {

        try {
            ModelMapper modelMapper = new ModelMapper();
            Feature thisFeature = featureDAO.get(id);
            if (thisFeature != null) {
                FeatureDO tempFeature = modelMapper.map(thisFeature, FeatureDO.class);
                return JSONResponseWrapper.getResponseInstance(tempFeature);
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
            //delete all the business cases
            caseDAO.deleteByFeature(id);
                if (featureDAO.delete(id)) {
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
    public JSONResponseWrapper getFeatursByProject(Integer projectId) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<Feature> features = featureDAO.getAllForProject(projectId);
            ArrayList<FeatureDO> projectFeatures = new ArrayList<FeatureDO>();
            if (CollectionUtils.isNotEmpty(features)) {
                for (Feature feature : features) {
                    FeatureDO tempFeature = modelMapper.map(feature, FeatureDO.class);
                    projectFeatures.add(tempFeature);
                }
            }
            System.out.println("Response : " + projectFeatures);
            return JSONResponseWrapper.getResponseInstance(projectFeatures);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }
}
