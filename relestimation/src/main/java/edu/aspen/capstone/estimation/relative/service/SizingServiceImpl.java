/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.SizingDAO;
import edu.aspen.capstone.estimation.relative.domain.SizingDO;
import edu.aspen.capstone.estimation.relative.entity.Sizing;
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
public class SizingServiceImpl implements SizingService {

    @Autowired
    SizingDAO sizingDAO;

    @Override
    public JSONResponseWrapper listAll() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<SizingDO> availableSizings = new ArrayList<SizingDO>();
            List<Sizing> sizings = sizingDAO.listAll();
            if (CollectionUtils.isNotEmpty(sizings)) {
                for (Sizing size : sizings) {
                    SizingDO tempSize = modelMapper.map(size, SizingDO.class);
                    availableSizings.add(tempSize);
                }
            }

            return JSONResponseWrapper.getResponseInstance(availableSizings);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

}