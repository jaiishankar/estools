package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Feature;
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
@Repository("FeatureDAO")
@Transactional
public class FeatureDAOImpl implements FeatureDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Feature add(Feature fea) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(fea);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().refresh(fea);
            return fea;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Feature update(Feature fea) {
        try {
            return this.add(fea);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Feature get(Integer fId) {
        try {
            return (Feature) sessionFactory.getCurrentSession().get(Feature.class, fId);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean delete(Integer fId) {
        try {
            sessionFactory.getCurrentSession().delete(this.get(fId));
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Feature> getAllForProject(Integer prjId) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("Feature.findByProject");
        query.setInteger("projectId", prjId);
        List<Feature> myFeatures = query.list();
        if (!myFeatures.isEmpty()) {
            return myFeatures;
        }
        return null;
    }

    @Override
    public Boolean deleteAllByProject(Integer projectId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("Feature.deleteByProject");
            query.setInteger("projectId", projectId);
            query.executeUpdate();
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

}
