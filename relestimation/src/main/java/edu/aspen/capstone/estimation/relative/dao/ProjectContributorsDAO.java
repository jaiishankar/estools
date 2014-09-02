package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ProjectContributors;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface ProjectContributorsDAO {

    List<ProjectContributors> getByUsers(Integer id);

    List<ProjectContributors> getByProject(Integer id);

    List<ProjectContributors> getByGroup(Integer id);
    
    Boolean deleteAllByProject(Integer projectId);

    ProjectContributors get(Integer id);

    ProjectContributors save(ProjectContributors data);

    Boolean delete(Integer id);

}
