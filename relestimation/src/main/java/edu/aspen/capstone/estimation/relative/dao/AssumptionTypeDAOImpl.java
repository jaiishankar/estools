package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.AssumptionType;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jaiishankar
 */
@Repository("AssumptionTypeDAO")
@Transactional
public class AssumptionTypeDAOImpl implements AssumptionTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<AssumptionType> listAll() {
        try {
            return (List<AssumptionType>) sessionFactory.getCurrentSession().createQuery("from "
                    + AssumptionType.class.getName()).list();
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public AssumptionType saveOrUpdate(AssumptionType type) {
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
    public AssumptionType getById(Integer id) {
        try {
            return (AssumptionType) sessionFactory.getCurrentSession().get(AssumptionType.class, id);
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

}
