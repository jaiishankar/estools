package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.BusinesscaseDAO;
import edu.aspen.capstone.estimation.relative.domain.BusinesscaseDO;
import edu.aspen.capstone.estimation.relative.entity.Businesscase;
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
public class BusinesscaseServiceImpl implements BusinesscaseService {

    @Autowired
    BusinesscaseDAO caseDAO;

    @Override
    public JSONResponseWrapper add(BusinesscaseDO bc) {
        return this.update(bc);
    }

    @Override
    public JSONResponseWrapper update(BusinesscaseDO bc) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Businesscase myasptyp = modelMapper.map(bc, Businesscase.class);

            Businesscase tempCase = caseDAO.update(myasptyp);
            if (tempCase != null) {
                BusinesscaseDO response = modelMapper.map(tempCase, BusinesscaseDO.class);
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
            Businesscase thisCase = caseDAO.get(id);
            if (thisCase != null) {
                BusinesscaseDO tempCase = modelMapper.map(thisCase, BusinesscaseDO.class);
                return JSONResponseWrapper.getResponseInstance(tempCase);
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
            if (caseDAO.delete(id)) {
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
    public JSONResponseWrapper getCasesForFeature(Integer fId) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<Businesscase> cases = caseDAO.getAllCasesForFeature(fId);
            ArrayList<BusinesscaseDO> featureCases = new ArrayList<BusinesscaseDO>();
            if (CollectionUtils.isNotEmpty(cases)) {
                for (Businesscase bcase : cases) {
                    BusinesscaseDO tempFeature = modelMapper.map(bcase, BusinesscaseDO.class);
                    featureCases.add(tempFeature);
                }
            }

            return JSONResponseWrapper.getResponseInstance(featureCases);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

}
