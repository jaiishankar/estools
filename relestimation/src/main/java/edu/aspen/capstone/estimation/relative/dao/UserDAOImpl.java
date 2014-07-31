package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ApplicationUser;
import edu.aspen.capstone.estimation.relative.utils.JSONExceptionWrapper;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
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
@Repository("UserDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ApplicationUser addUser(ApplicationUser usr) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(usr);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().refresh(usr);
            return usr;
        } catch (HibernateException he) {
            he.printStackTrace();
            return null;
        }

    }

    @Override
    public ApplicationUser getByID(Integer id) {
        return (ApplicationUser) sessionFactory.getCurrentSession().get(ApplicationUser.class, id);
    }

    @Override
    public ApplicationUser getByName(String name) {
        List<ApplicationUser> users = sessionFactory.getCurrentSession().createQuery("from "
                + edu.aspen.capstone.estimation.relative.entity.ApplicationUser.class.getName()
                + " where uname ='" + name + "'").list();
        if (users.size() <= 0) {
            System.out.println("returning null");
            return null;
        } else {
            System.out.println("Found the record " + users.get(0).getId());
            return users.get(0);
        }
    }

    @Override
    public JSONResponseWrapper deactiveUser(Long id) {
        JSONExceptionWrapper msg = new JSONExceptionWrapper("Decativate error", "User not found");
        ApplicationUser dbUser = (ApplicationUser) sessionFactory.getCurrentSession().get(ApplicationUser.class, id);
        if (dbUser != null) {
            dbUser.setIsLoggedIn(Boolean.FALSE);
            dbUser.setIsActive(Boolean.FALSE);
            this.addUser(dbUser);
            return JSONResponseWrapper.getDefaultSuccessResponseInstance("User deactivated");
        } else {
            return JSONResponseWrapper.getErrorResponseInstance(msg);
        }

    }

    @Override
    public List<ApplicationUser> listAll() {
        return (List<ApplicationUser>) sessionFactory.getCurrentSession().createQuery("from "
                + ApplicationUser.class.getName()).list();
    }

    @Override
    public Object logon(ApplicationUser usr) {
        try {
            ApplicationUser dbUser;
            JSONExceptionWrapper errorResponse = new JSONExceptionWrapper("", "");
            if (usr.getId() != null && usr.getId() > 0) {
                dbUser = (ApplicationUser) sessionFactory.getCurrentSession().get(ApplicationUser.class, usr.getId());
            } else {
                dbUser = this.getByName(usr.getUname());
            }
            boolean success = false;

        //this block is made very simple initially so that in future
            //we may need to have pretty complex logic and even return object 
            //will be changed eventually.
            System.out.println("in dao signup " + dbUser);
            if (dbUser != null) {
                System.out.println("User is not null");
                if (dbUser.getIsActive()) {
                    System.out.println("user is active");
                    if (dbUser.getUname().equals(usr.getUname())) {
                        System.out.println("uname matchs the records");
                        if (dbUser.getPasscode().equals(usr.getPasscode())) {
                            System.out.println("passcode matchs");
                            success = true;
                            dbUser.setIsLoggedIn(true);
                            System.out.println("updating the records");
                            dbUser = this.addUser(dbUser);
                        } else {
                            errorResponse = new JSONExceptionWrapper("Logon Error", "Password mis-match");
                        }
                    } else {
                        errorResponse = new JSONExceptionWrapper("Logon Error", "username mis-match");
                    }
                } else {
                    errorResponse = new JSONExceptionWrapper("Logon Error", "User not active, please activate");
                }
            } else {
                errorResponse = new JSONExceptionWrapper("Logon Error", "User not available, signup to add user");
            }
            System.out.println("in dao signup ==> " + success);
            return success ? dbUser : errorResponse;
        } catch (Exception e) {
            return new JSONExceptionWrapper("Logon Error", e);
        }

    }

    @Override
    public List<ApplicationUser> listAllLoggedUser() {
        List<ApplicationUser> users = sessionFactory.getCurrentSession().createQuery("from "
                + edu.aspen.capstone.estimation.relative.entity.ApplicationUser.class.getName() + " where LOGGED_IN = 1").list();
        return users;
    }

    @Override
    public boolean logout(ApplicationUser usr) {

        System.out.println(usr.toString());
        ApplicationUser dbUser;
        if (usr.getId() != null && usr.getId() > 0) {
            dbUser = (ApplicationUser) sessionFactory.getCurrentSession().get(ApplicationUser.class, usr.getId());
        } else {
            dbUser = this.getByName(usr.getUname());
        }

        if (dbUser != null) {
            dbUser.setIsLoggedIn(false);
            this.addUser(dbUser);
        }

        return true;
    }
}
