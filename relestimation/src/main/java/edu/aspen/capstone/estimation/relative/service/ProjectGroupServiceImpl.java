
package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.ProjectGroupDAO;
import edu.aspen.capstone.estimation.relative.domain.ProjectGroupDO;
import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import edu.aspen.capstone.estimation.relative.utils.DOUtils;
import edu.aspen.capstone.estimation.relative.utils.JSONExceptionWrapper;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
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
    public JSONResponseWrapper addAssociations(Integer projectId, Integer[] groupIds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONResponseWrapper removeAssociations(Integer projectId, Integer[] groupIds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
