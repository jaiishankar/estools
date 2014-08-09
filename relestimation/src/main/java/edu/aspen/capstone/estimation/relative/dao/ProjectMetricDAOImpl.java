/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ProjectMetric;
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
@Repository("ProjectMetricDAO")
@Transactional
public class ProjectMetricDAOImpl implements ProjectMetricDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProjectMetric> getAllByProject(Integer prjId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectMetric.findByProjectId");
            query.setInteger("projectId", prjId);
            List<ProjectMetric> myMetrics = query.list();

            if (!myMetrics.isEmpty()) {
                return myMetrics;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProjectMetric> saveorUpdateMetricsForProject(List<ProjectMetric> values) {
        //iterate towards each group and insert and update the list
        List<ProjectMetric> updated = new ArrayList<ProjectMetric>();
        try {
            if (CollectionUtils.isNotEmpty(values)) {
                for (ProjectMetric grp : values) {
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
    public Boolean deleteMetricsForProject(List<ProjectMetric> values) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("ProjectMetric.deleteByProjectId");
            if (CollectionUtils.isNotEmpty(values)) {
                for (ProjectMetric grp : values) {
                    query.setInteger("projectId", grp.getProjectId());
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

    @Override
    public ProjectMetric save(ProjectMetric data) {
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
    public ProjectMetric get(Integer id) {
        try {
            return (ProjectMetric) sessionFactory.getCurrentSession().get(ProjectMetric.class, id);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

}
