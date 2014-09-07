package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Assumption;
import edu.aspen.capstone.estimation.relative.entity.AssumptionType;
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
 * @author JaiShankar
 */
@Repository("AssumptionDAO")
@Transactional
public class AssumptionDAOImpl implements AssumptionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Assumption> listAllByCase(Integer caseId) {

        Query query = sessionFactory.getCurrentSession().getNamedQuery("Assumption.findByBusinesscase");
        query.setInteger("caseId", caseId);
        List<Assumption> myAssumptions = query.list();
        if (!myAssumptions.isEmpty()) {
            return myAssumptions;
        }
        return null;
    }

    @Override
    public Assumption saveOrUpdate(Assumption type) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(type);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().refresh(type);
            return type;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Assumption getById(Integer id) {
        try {
            return (Assumption) sessionFactory.getCurrentSession().get(Assumption.class, id);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            sessionFactory.getCurrentSession().delete(this.getById(id));
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteByCase(Integer caseId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("Assumption.deleteByBusinesscase");
            query.setInteger("caseId", caseId);
            query.executeUpdate();
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }
}
