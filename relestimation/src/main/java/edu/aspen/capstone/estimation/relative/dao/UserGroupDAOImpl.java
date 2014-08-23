package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.UserGroup;
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
@Repository("UserGroupDAO")
@Transactional
public class UserGroupDAOImpl implements UserGroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserGroup> getAllByUser(Integer userId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("UserGroup.findByUserId");
            query.setInteger("id", userId);
            List<UserGroup> myUserGroups = query.list();

            if (!myUserGroups.isEmpty()) {
                return myUserGroups;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserGroup> updateGroupsForUser(List<UserGroup> groups) {
        List<UserGroup> updated = new ArrayList<UserGroup>();
        try {
            if (CollectionUtils.isNotEmpty(groups)) {
                for (UserGroup grp : groups) {
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
    public Boolean deleteGroupsForUser(List<UserGroup> groups) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("UserGroup.deletByUserId");
            if (CollectionUtils.isNotEmpty(groups)) {
                for (UserGroup grp : groups) {
                    query.setInteger("userId", grp.getUserId());
                    query.setInteger("groupId", grp.getGroupId());
                    query.executeUpdate();
                }
                return true;
            } else {
                return false;
            }
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserGroup> getAllByGroup(Integer groupId) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("UserGroup.findByGroupId");
            query.setInteger("id", groupId);
            List<UserGroup> myUserGroups = query.list();

            if (!myUserGroups.isEmpty()) {
                return myUserGroups;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }
}
