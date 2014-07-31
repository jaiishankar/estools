package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.ProjectDAO;
import edu.aspen.capstone.estimation.relative.domain.ProjectDO;
import edu.aspen.capstone.estimation.relative.entity.Project;
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
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public JSONResponseWrapper getAllProjects() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<Project> projects = projectDAO.listAll();
            ArrayList<ProjectDO> appProjects = new ArrayList<ProjectDO>();
            for (Project project : projects) {
                ProjectDO tempPrj = modelMapper.map(project, ProjectDO.class);
                appProjects.add(tempPrj);
            }
            return JSONResponseWrapper.getResponseInstance(appProjects);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper getProjectsByUserId(Integer id) {
        //needs to do a double orchestraction 
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<Project> projects = projectDAO.listAllByUser(id);
            //projects.addAll(projectDAO.listAllByOwner(id));

            ArrayList<ProjectDO> appProjects = new ArrayList<ProjectDO>();
            for (Project project : projects) {
                ProjectDO tempPrj = modelMapper.map(project, ProjectDO.class);
                appProjects.add(tempPrj);
            }
            return JSONResponseWrapper.getResponseInstance(appProjects);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper saveOrUpdateProject(ProjectDO prj) {
        ModelMapper modelMapper = new ModelMapper();
        Project prjt = modelMapper.map(prj, Project.class);
        if (prj.getId() != null) {
            //update call
            Project updated = projectDAO.updateProject(prjt);
            ProjectDO updatedPrj = modelMapper.map(updated, ProjectDO.class);
            return JSONResponseWrapper.getResponseInstance(updatedPrj);
        } else {
            Project added = projectDAO.addProject(prjt);
            ProjectDO addedPrj = modelMapper.map(added, ProjectDO.class);
            return JSONResponseWrapper.getResponseInstance(addedPrj);
        }

    }

    @Override
    public JSONResponseWrapper deleteProject(Integer id) {
        try {
            if (projectDAO.deleteProject(id)) {
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
    public JSONResponseWrapper getProjectsById(Integer id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Project prj = projectDAO.getProjectById(id);
            ProjectDO updatedPrj = modelMapper.map(prj, ProjectDO.class);

            if (updatedPrj != null) {
                return JSONResponseWrapper.getResponseInstance(updatedPrj);
            } else {
                return JSONResponseWrapper.getDefaultFailResponseInstance();
            }
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper getProjectsByOwner(Integer id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<Project> projects = projectDAO.listAllByOwner(id);
            ArrayList<ProjectDO> appProjects = new ArrayList<ProjectDO>();
            for (Project project : projects) {
                ProjectDO tempPrj = modelMapper.map(project, ProjectDO.class);
                appProjects.add(tempPrj);
            }
            return JSONResponseWrapper.getResponseInstance(appProjects);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }
}
