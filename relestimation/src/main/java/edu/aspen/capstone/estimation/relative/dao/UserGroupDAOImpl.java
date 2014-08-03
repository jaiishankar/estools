package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.UserGroup;
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
@Repository("UserGroupDAO")
@Transactional
public class UserGroupDAOImpl implements UserGroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserGroup> getAllByProject(Integer userId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectGroups.findByProjectId");
            query.setInteger("id", userId);
            List<UserGroup> myProjects = query.list();

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
    public Boolean updateGroupsForUser(Integer userId, List<UserGroup> groups) {
        try {
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteGroupsForUser(Integer userId) {
        try {
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

}
