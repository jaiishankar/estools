package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ProjectFeatureSizing;
import java.util.List;

/**
 * DAO main service
 *
 * @author jaiishankar
 */
public interface ProjectFeatureSizingDAO {

    List<ProjectFeatureSizing> getByProject(Integer id);

    List<ProjectFeatureSizing> getByFeature(Integer id);

    ProjectFeatureSizing get(Integer id);

    ProjectFeatureSizing save(ProjectFeatureSizing data);

    Boolean delete(Integer id);
}
