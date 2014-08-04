package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ProjectGroups;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
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
    public List<ProjectGroups> updateGroupsForProject(List<ProjectGroups> groups) {
        //iterate towards each group and insert and update the list
        List<ProjectGroups> updated = new ArrayList<ProjectGroups>();
        try {
            if (CollectionUtils.isNotEmpty(groups)) {
                for (ProjectGroups grp : groups) {
                    sessionFactory.getCurrentSession().saveOrUpdate(grp);
                    sessionFactory.getCurrentSession().flush();
                    sessionFactory.getCurrentSession().refresh(grp);
                    updated.add(grp);
                }
            }
            return updated;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteGroupsForProject(List<ProjectGroups> groups) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectGroups.deleteByGroupAndProjectId");
            if (CollectionUtils.isNotEmpty(groups)) {
                for (ProjectGroups grp : groups) {
                     query.setInteger("projectId", grp.getProjectId());
                     query.setInteger("groupId", grp.getGroupId());
                     query.executeUpdate();
                }
                return true;
            } else {
                return false;
            }
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return false;
        }
    }

}
