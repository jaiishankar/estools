package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.AssumptionDAO;
import edu.aspen.capstone.estimation.relative.domain.AssumptionDO;
import edu.aspen.capstone.estimation.relative.entity.Assumption;
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
 * @author JaiShankar
 */
@Service
public class AssumptionServiceImpl implements AssumptionService {

    @Autowired
    private AssumptionDAO dao;

    @Override
    public JSONResponseWrapper listAllByCase(Integer caseId) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<Assumption> assumptions = dao.listAllByCase(caseId);
            List<AssumptionDO> caseAssumptions = new ArrayList<AssumptionDO>();
            if (CollectionUtils.isNotEmpty(assumptions)) {
                for(Assumption assumption : assumptions){
                    AssumptionDO temp = modelMapper.map(assumption, AssumptionDO.class);
                    caseAssumptions.add(temp);
                }
            }
            return JSONResponseWrapper.getResponseInstance(caseAssumptions);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper saveOrUpdate(AssumptionDO type) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            return JSONResponseWrapper.getResponseInstance(null);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper getById(Integer id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            return JSONResponseWrapper.getResponseInstance(null);
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

    @Override
    public JSONResponseWrapper deleteByCase(Integer caseId) {
        try {
            if (dao.deleteByCase(caseId)) {
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
