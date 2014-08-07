package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Priority;
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
@Repository("PriorityDAO")
@Transactional
public class PriorityDAOImpl implements PriorityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Priority> listAll() {
        try {
            return (List<Priority>) sessionFactory.getCurrentSession().createQuery("from "
                    + Priority.class.getName()).list();
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Priority getById(Integer id) {
        try {
            return (Priority) sessionFactory.getCurrentSession().get(Priority.class, id);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Priority saveOrUpdate(Priority data) {
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
    public Boolean deletePriority(Integer id) {
        try {
            sessionFactory.getCurrentSession().delete(this.getById(id));
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

}
