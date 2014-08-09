package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.AssumptionTypeDAO;
import edu.aspen.capstone.estimation.relative.domain.AssumptionTypeDO;
import edu.aspen.capstone.estimation.relative.entity.AssumptionType;
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
public class AssumptionTypeServiceImpl implements AssumptionTypeService {

    @Autowired
    AssumptionTypeDAO assumTypeDAO;

    @Override
    public JSONResponseWrapper getAll() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<AssumptionType> assmTyps = assumTypeDAO.listAll();
            ArrayList<AssumptionTypeDO> appGroups = new ArrayList<AssumptionTypeDO>();
            if (CollectionUtils.isNotEmpty(assmTyps)) {
                for (AssumptionType assumpType : assmTyps) {
                    AssumptionTypeDO tempGrp = modelMapper.map(assumpType, AssumptionTypeDO.class);
                    appGroups.add(tempGrp);
                }
            }
            return JSONResponseWrapper.getResponseInstance(appGroups);

        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper get(Integer id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            AssumptionType group = assumTypeDAO.getById(id);
            AssumptionTypeDO tempGrp = modelMapper.map(group, AssumptionTypeDO.class);
            return JSONResponseWrapper.getResponseInstance(tempGrp);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper save(AssumptionTypeDO asptyp) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            AssumptionType myasptyp = modelMapper.map(asptyp, AssumptionType.class);

            AssumptionType tempGrp = assumTypeDAO.saveOrUpdate(myasptyp);
            if (tempGrp != null) {
                AssumptionTypeDO response = modelMapper.map(tempGrp, AssumptionTypeDO.class);
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
            if (assumTypeDAO.delete(id)) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

}
