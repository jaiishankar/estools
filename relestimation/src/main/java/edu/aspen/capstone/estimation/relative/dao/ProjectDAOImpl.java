package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Project;
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
@Repository("ProjectDAO")
@Transactional
public class ProjectDAOImpl implements ProjectDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Project> listAll() {
        try {
            return (List<Project>) sessionFactory.getCurrentSession().createQuery("from "
                    + Project.class.getName()).list();
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Project getProjectById(Integer id) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("Project.findById");
            query.setInteger("id", id);
            List<Project> myProjects = query.list();

            if (!myProjects.isEmpty()) {
                return myProjects.get(0);
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Project updateProject(Project prj) {
        try {
            return this.addProject(prj);
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public Project addProject(Project prj) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(prj);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().refresh(prj);
            return prj;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Project> listAllByOwner(Integer id) {
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("Project.findByOwnerId");
            query.setInteger("ownerId", id);
            List<Project> myProjects = query.list();

            if (!myProjects.isEmpty()) {
                return myProjects;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Project> listAllByUser(Integer id) {
        try {
            String myQuery = "SELECT * FROM tms.project \n"
                    + "where id in (select \n"
                    + "                project_id\n"
                    + "            from\n"
                    + "                project_groups\n"
                    + "            where\n"
                    + "            group_id in (\n"
                    + "                select \n"
                    + "                    group_id\n"
                    + "                from\n"
                    + "                    user_group\n"
                    + "                where\n"
                    + "                    user_id = ?)\n"
                    + "             )";
            Query query = sessionFactory.getCurrentSession().createSQLQuery(myQuery).addEntity(Project.class);
            query.setInteger(0, id);
            List<Project> otherProjects = query.list();
            if (!otherProjects.isEmpty()) {
                return otherProjects;
            }
            return null;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return null;
        }

    }

    @Override
    public Boolean deleteProject(Integer id) {
        try {
            sessionFactory.getCurrentSession().delete(this.getProjectById(id));
            return true;
        } catch (HibernateException hbe) {
            hbe.printStackTrace();
            return false;
        }
    }

}

//try{return null;} catch(HibernateException hbe){hbe.printStackTrace();return null;}
