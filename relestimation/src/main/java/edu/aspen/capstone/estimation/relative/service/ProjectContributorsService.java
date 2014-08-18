package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.ProjectContributorsDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface ProjectContributorsService {

    JSONResponseWrapper getByUsers(Integer id);

    JSONResponseWrapper getByProject(Integer id);

    JSONResponseWrapper getByGroup(Integer id);

    JSONResponseWrapper get(Integer id);

    JSONResponseWrapper save(ProjectContributorsDO data);

    JSONResponseWrapper delete(Integer id);
}
