package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ProjectContributors;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DATA ACCESS Class 
 * @author jaiishankar
 */
@Repository("ProjectContributorsDAO")
@Transactional
public class ProjectContributorsDAOImpl implements ProjectContributorsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProjectContributors> getByUsers(Integer id) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectContributors.findByUserId");
            query.setInteger("userId", id);
            List<ProjectContributors> myList = query.list();

            if (!myList.isEmpty()) {
                return myList;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProjectContributors> getByProject(Integer id) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectContributors.findByProjectId");
            query.setInteger("projectId", id);
            List<ProjectContributors> myList = query.list();

            if (!myList.isEmpty()) {
                return myList;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProjectContributors> getByGroup(Integer id) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectContributors.findByGroupId");
            query.setInteger("groupId", id);
            List<ProjectContributors> myList = query.list();

            if (!myList.isEmpty()) {
                return myList;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public ProjectContributors get(Integer id) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectContributors.findById");
            query.setInteger("id", id);
            List<ProjectContributors> myList = query.list();

            if (!myList.isEmpty()) {
                return myList.get(0);
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public ProjectContributors save(ProjectContributors data) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(data);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().refresh(data);
            return data;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            sessionFactory.getCurrentSession().delete(this.get(id));
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteAllByProject(Integer projectId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectContributors.deleteByProject");
            query.setInteger("projectId", projectId);
            query.executeUpdate();
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

}
