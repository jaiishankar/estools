package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Project;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface ProjectDAO {

    Project getProjectById(Integer id);

    Project updateProject(Project prj);

    Project addProject(Project prj);

    List<Project> listAllByOwner(Integer id);

    List<Project> listAllByUser(Integer id);

    List<Project> listAll();
    
    Boolean deleteProject(Integer id);
}
