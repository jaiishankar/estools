package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Project;
import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jaiishankar
 */
@Repository("ProjectGroupDAO")
@Transactional
public class ProjectGroupDAOImpl implements ProjectGroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProjectGroups> getAllByProject(Integer projectId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectGroups.findByProjectId");
            query.setInteger("projectId", projectId);
            List<ProjectGroups> myProjects = query.list();

            if (!myProjects.isEmpty()) {
                return myProjects;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateGroupsForProject(Integer projectId, List<ProjectGroups> groups) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
