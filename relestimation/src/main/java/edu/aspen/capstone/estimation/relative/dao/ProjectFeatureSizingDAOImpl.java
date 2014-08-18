package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ProjectFeatureSizing;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data Access Object for Project Feature sizing data
 *
 * @author jaiishankar
 */
@Repository("ProjectFeatureSizingDAO")
@Transactional
public class ProjectFeatureSizingDAOImpl implements ProjectFeatureSizingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProjectFeatureSizing> getByProject(Integer id) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectFeatureSizing.findByProjectId");
            query.setInteger("projectId", id);
            List<ProjectFeatureSizing> myList = query.list();

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
    public List<ProjectFeatureSizing> getByFeature(Integer id) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectFeatureSizing.findByFeatureId");
            query.setInteger("featureId", id);
            List<ProjectFeatureSizing> myList = query.list();

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
    public ProjectFeatureSizing get(Integer id) {
        try {
            return (ProjectFeatureSizing) sessionFactory.getCurrentSession().get(ProjectFeatureSizing.class, id);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public ProjectFeatureSizing save(ProjectFeatureSizing data) {
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

}
