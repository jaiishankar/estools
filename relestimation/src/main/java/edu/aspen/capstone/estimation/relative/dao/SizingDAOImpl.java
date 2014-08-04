/*
 *  
 *  
 * 
 */

package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Sizing;
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
@Repository("SizingDAO")
@Transactional
public class SizingDAOImpl implements SizingDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Sizing> listAll() {
        try {
            return (List<Sizing>) sessionFactory.getCurrentSession().createQuery("from "
                    + Sizing.class.getName()).list();
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }
    
}
