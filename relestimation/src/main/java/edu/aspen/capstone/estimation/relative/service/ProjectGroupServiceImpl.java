package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.ProjectGroupDAO;
import edu.aspen.capstone.estimation.relative.domain.ProjectGroupDO;
import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import edu.aspen.capstone.estimation.relative.utils.DOUtils;
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
public class ProjectGroupServiceImpl implements ProjectGroupService {

    @Autowired
    ProjectGroupDAO projectgrpDAO;

    @Override
    public JSONResponseWrapper getGroupsForProject(Integer prjId) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<ProjectGroups> projects = projectgrpDAO.getAllByProject(prjId);
            ProjectGroupDO projectGroup = DOUtils.decode(projects);
            return JSONResponseWrapper.getResponseInstance(projectGroup);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper updateAssociations(ProjectGroupDO project) {
        try {
            List<ProjectGroups> currentList = projectgrpDAO.getAllByProject(project.getProjectId());
            List<ProjectGroups> reqList = DOUtils.encode(project);
            List<ProjectGroups> needsUpdate = new ArrayList<ProjectGroups>();
            List<ProjectGroups> needsDelete = new ArrayList<ProjectGroups>();
            
            //let us populate the needs insert&update list, we need to take the reqList 
            //and see if any not present in the current one.
            for(ProjectGroups grp: currentList){
                if(reqList.contains(grp)){
                    needsUpdate.add(grp);
                } else {
                    needsDelete.add(grp);
                }
            }
            
            for(ProjectGroups grp: reqList){
                if(!currentList.contains(grp)){
                    needsUpdate.add(grp);
                } 
            }
            
            //Now update insert and update needed and delete the ones we don't
            //need.
            List<ProjectGroups> updated = projectgrpDAO.updateGroupsForProject(needsUpdate);
            boolean result = projectgrpDAO.deleteGroupsForProject(needsDelete);
            return JSONResponseWrapper.getDefaultSuccessResponseInstance();
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(new JSONExceptionWrapper("Error", e));
        }
    }

}

//try{return JSONResponseWrapper.getResponseInstance("");} catch (Exception e) { return JSONResponseWrapper.getErrorResponseInstance(new JSONExceptionWrapper("Error", e));}
