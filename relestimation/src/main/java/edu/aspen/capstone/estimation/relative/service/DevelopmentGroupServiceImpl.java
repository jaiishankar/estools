package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.DevelopmentGroupDAO;
import edu.aspen.capstone.estimation.relative.domain.DevelopmentGroupDO;
import edu.aspen.capstone.estimation.relative.entity.DevelopmentGroup;
import edu.aspen.capstone.estimation.relative.utils.JSONExceptionWrapper;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jaiishankar
 */
@Service
public class DevelopmentGroupServiceImpl implements DevelopmentGroupService {

    @Autowired
    DevelopmentGroupDAO devGroupDAO;

    @Override
    public JSONResponseWrapper listAll() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<DevelopmentGroup> groups = devGroupDAO.listAll();
            ArrayList<DevelopmentGroupDO> appGroups = new ArrayList<DevelopmentGroupDO>();
            for (DevelopmentGroup group : groups) {
                DevelopmentGroupDO tempGrp = modelMapper.map(group, DevelopmentGroupDO.class);
                appGroups.add(tempGrp);
            }
            return JSONResponseWrapper.getResponseInstance(appGroups);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper listGroup(Integer id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            DevelopmentGroup group = devGroupDAO.getGroup(id);
            DevelopmentGroupDO tempGrp = modelMapper.map(group, DevelopmentGroupDO.class);
            return JSONResponseWrapper.getResponseInstance(tempGrp);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper saveGroup(DevelopmentGroupDO grp) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            DevelopmentGroup mygrp = modelMapper.map(grp, DevelopmentGroup.class);
            
            DevelopmentGroup tempGrp = devGroupDAO.addGroup(mygrp);
            return (tempGrp != null)
                    ? JSONResponseWrapper.getResponseInstance(tempGrp)
                    : JSONResponseWrapper.getDefaultFailResponseInstance();
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper deleteGroup(Integer grpId) {
        try {
            if (devGroupDAO.deleteGroup(grpId)) {
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
