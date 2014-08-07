package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.PriorityDAO;
import edu.aspen.capstone.estimation.relative.domain.PriorityDO;
import edu.aspen.capstone.estimation.relative.entity.Priority;
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
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    PriorityDAO priorityDAO;

    @Override
    public JSONResponseWrapper listAll() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<Priority> groups = priorityDAO.listAll();
            if (CollectionUtils.isNotEmpty(groups)) {
                ArrayList<PriorityDO> priorities = new ArrayList<PriorityDO>();
                for (Priority p : groups) {
                    PriorityDO temp = modelMapper.map(p, PriorityDO.class);
                    priorities.add(temp);
                }
                return JSONResponseWrapper.getResponseInstance(priorities);
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper getPriority(Integer id) {
        try {

            ModelMapper modelMapper = new ModelMapper();
            Priority p = priorityDAO.getById(id);
            if (p != null) {
                PriorityDO response = modelMapper.map(p, PriorityDO.class);
                return JSONResponseWrapper.getResponseInstance(response);
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper savePriority(PriorityDO data) {
        try {
            if (data != null) {
                ModelMapper modelMapper = new ModelMapper();
                Priority p = modelMapper.map(data, Priority.class);
                Priority returned = priorityDAO.saveOrUpdate(p);
                if (returned != null) {
                    PriorityDO finalresponse = modelMapper.map(returned, PriorityDO.class);
                    return JSONResponseWrapper.getResponseInstance(finalresponse);
                } else {
                    return JSONResponseWrapper.getDefaultFailResponseInstance();
                }

            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper deletePriority(Integer id) {
        try {
            if (priorityDAO.deletePriority(id)) {
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
