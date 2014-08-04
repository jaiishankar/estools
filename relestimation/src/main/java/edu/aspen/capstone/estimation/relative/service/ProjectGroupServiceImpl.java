package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.ProjectGroupDAO;
import edu.aspen.capstone.estimation.relative.domain.ProjectGroupDO;
import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import edu.aspen.capstone.estimation.relative.utils.DOUtils;
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
public class ProjectGroupServiceImpl implements ProjectGroupService {

    @Autowired
    ProjectGroupDAO projectgrpDAO;

    @Override
    public JSONResponseWrapper getGroupsForProject(Integer prjId) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<ProjectGroups> projects = projectgrpDAO.getAllByProject(prjId);
            ProjectGroupDO projectGroup = new ProjectGroupDO();
            projectGroup.setProjectId(prjId);
            if (CollectionUtils.isNotEmpty(projects)) {
                projectGroup = DOUtils.decodeProjectGroup(projects);
            }
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
            List<ProjectGroups> reqList = DOUtils.encodeProjectGroup(project);
            List<ProjectGroups> needsUpdate = new ArrayList<ProjectGroups>();
            List<ProjectGroups> needsDelete = new ArrayList<ProjectGroups>();
            //let us populate the needs insert&update list, we need to take the reqList 
            //and see if any not present in the current one.
            if (CollectionUtils.isNotEmpty(currentList)) {
                for (ProjectGroups grp : currentList) {
                    if (DOUtils.isGroupExistsForProjectGroups(reqList, grp)) {
                        needsUpdate.add(grp);
                    } else {
                        needsDelete.add(grp);
                    }
                }

                for (ProjectGroups grp : reqList) {
                    if (!DOUtils.isGroupExistsForProjectGroups(currentList, grp)) {
                        needsUpdate.add(grp);
                    }
                }
            } else {
                needsUpdate.addAll(reqList);
            }

            
            List<ProjectGroups> updated = null;
            boolean result = false;
            //Now update insert and update needed and delete the ones we don't
            //need.
            if (CollectionUtils.isNotEmpty(needsUpdate)) {
                updated = projectgrpDAO.updateGroupsForProject(needsUpdate);
            }
            if (CollectionUtils.isNotEmpty(needsDelete)) {
                result = projectgrpDAO.deleteGroupsForProject(needsDelete);
            }

            if (CollectionUtils.isNotEmpty(needsUpdate)
                    && CollectionUtils.isNotEmpty(updated)
                    && CollectionUtils.isNotEmpty(needsDelete) && result) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            } else if (CollectionUtils.isNotEmpty(needsUpdate)
                    && CollectionUtils.isNotEmpty(updated)
                    && CollectionUtils.isEmpty(needsDelete) && !result) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            } else if (CollectionUtils.isEmpty(needsUpdate)
                    && CollectionUtils.isEmpty(updated)
                    && CollectionUtils.isNotEmpty(needsDelete) && result) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(new JSONExceptionWrapper("Error", e));
        }
    }

}

//try{return JSONResponseWrapper.getResponseInstance("");} catch (Exception e) { return JSONResponseWrapper.getErrorResponseInstance(new JSONExceptionWrapper("Error", e));}
