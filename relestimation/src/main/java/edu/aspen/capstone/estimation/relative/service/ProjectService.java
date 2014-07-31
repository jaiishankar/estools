package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.ProjectDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface ProjectService {

    //R 
    //Get the exact project
    JSONResponseWrapper getProjectsById(Integer id);
    
    //Gets all the projects for the logged in user's group
    JSONResponseWrapper getProjectsByUserId(Integer id);
    
    //Gets all the projects owned by this logged in user
    JSONResponseWrapper getProjectsByOwner(Integer id);
    
    //R
    //gets all the projects in the system
    JSONResponseWrapper getAllProjects();
    
    //update Project details C U
    JSONResponseWrapper saveOrUpdateProject(ProjectDO prj);
    
    //D
    //delete project by id
    JSONResponseWrapper deleteProject(Integer id);
    
}
