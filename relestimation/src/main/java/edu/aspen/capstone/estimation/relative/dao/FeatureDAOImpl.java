package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Feature;
import org.hibernate.HibernateException;
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
            return null;
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

}
