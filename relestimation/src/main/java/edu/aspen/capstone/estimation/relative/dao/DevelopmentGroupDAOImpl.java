/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.DevelopmentGroup;
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
@Repository("DevelopmentGroupDAO")
@Transactional
public class DevelopmentGroupDAOImpl implements DevelopmentGroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DevelopmentGroup addGroup(DevelopmentGroup grp) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(grp);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().refresh(grp);
            return grp;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DevelopmentGroup> listAll() {
        try {
            return (List<DevelopmentGroup>) sessionFactory.getCurrentSession().createQuery("from "
                    + DevelopmentGroup.class.getName()).list();
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public DevelopmentGroup getGroup(Integer id) {
        try {
            return (DevelopmentGroup) sessionFactory.getCurrentSession().get(DevelopmentGroup.class, id);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public DevelopmentGroup updateGroup(DevelopmentGroup grp) {
        try {
            return this.addGroup(grp);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteGroup(Integer id) {
        try {
            sessionFactory.getCurrentSession().delete(this.getGroup(id));
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return false;
        }
    }

}
//try{return null;} catch(HibernateException hbe){hbe.printStackTrace();return null;}
