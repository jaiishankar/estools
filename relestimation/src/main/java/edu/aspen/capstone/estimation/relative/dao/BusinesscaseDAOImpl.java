package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Businesscase;
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
@Repository("BusinesscaseDAO")
@Transactional
public class BusinesscaseDAOImpl implements BusinesscaseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param bc
     * @return
     */
    @Override
    public Businesscase add(Businesscase bc) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(bc);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().refresh(bc);
            return bc;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Businesscase update(Businesscase bc) {
        try {
            return this.add(bc);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Businesscase get(Integer bcId) {
        try {
            return (Businesscase) sessionFactory.getCurrentSession().get(Businesscase.class, bcId);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean delete(Integer bcId) {
        try {
            sessionFactory.getCurrentSession().delete(this.get(bcId));
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Businesscase> getAllCasesForFeature(Integer fId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("Businesscase.findByFeature");
            query.setInteger("featureId", fId);
            List<Businesscase> myFeatures = query.list();
            if (!myFeatures.isEmpty()) {
                return myFeatures;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteByFeature(Integer featureId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("Businesscase.deletebyFeature");
            query.setInteger("featureId", featureId);
            query.executeUpdate();
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

}
