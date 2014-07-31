package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.dao.UserDAO;
import edu.aspen.capstone.estimation.relative.domain.UserDO;
import edu.aspen.capstone.estimation.relative.entity.ApplicationUser;
import edu.aspen.capstone.estimation.relative.utils.JSONExceptionWrapper;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jaiishankar
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserDAO usersDAO;

    @Override
    public JSONResponseWrapper signup(UserDO webusr) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ApplicationUser usr = modelMapper.map(webusr, ApplicationUser.class);
            usr.setIsActive(Boolean.TRUE);
            ApplicationUser createdUsr = usersDAO.addUser(usr);
            UserDO tempUsr = modelMapper.map(createdUsr, UserDO.class);
            System.out.println(createdUsr);
            return (tempUsr != null)
                    ? JSONResponseWrapper.getResponseInstance(tempUsr)
                    : JSONResponseWrapper.getDefaultFailResponseInstance();
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper deactivate(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JSONResponseWrapper logon(UserDO webusr) {

        try {
            ModelMapper modelMapper = new ModelMapper();
            ApplicationUser usr = modelMapper.map(webusr, ApplicationUser.class);
            Object result = usersDAO.logon(usr);

            if (result instanceof ApplicationUser) {
                System.out.println("Got response from DAO");
                UserDO loggedUser = modelMapper.map(result, UserDO.class);
                System.out.println("Returning to caller");
                return JSONResponseWrapper.getResponseInstance(
                        loggedUser);
            }
            System.out.println("Comming here too.");
            return JSONResponseWrapper.getErrorResponseInstance((JSONExceptionWrapper) result);

        } catch (Exception e) {

            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e.getMessage()));
        }
    }

    @Override
    public JSONResponseWrapper checkAvailablitiy(String name) {
        try {
            ApplicationUser currentUser = usersDAO.getByName(name);
            if (currentUser != null) {
                return JSONResponseWrapper.getErrorResponseInstance(
                        new JSONExceptionWrapper("Error", name
                                + " username is already present."));
            }

            return JSONResponseWrapper.getDefaultSuccessResponseInstance(" Given name:" + name + " is available");
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper listAllLoggedUsers() {
        try {
            return JSONResponseWrapper.getResponseInstance(
                    usersDAO.listAllLoggedUser());
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper logout(UserDO webusr) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ApplicationUser usr = modelMapper.map(webusr, ApplicationUser.class);

            Boolean result = usersDAO.logout(usr);

            if (result) {
                return JSONResponseWrapper.getDefaultSuccessResponseInstance();
            }
            return JSONResponseWrapper.getDefaultFailResponseInstance();

        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper listAll() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<ApplicationUser> users = usersDAO.listAll();
            ArrayList<UserDO> appusers = new ArrayList<UserDO>();
            for (ApplicationUser user : users) {
                UserDO tempUsr = modelMapper.map(user, UserDO.class);
                appusers.add(tempUsr);
            }
            return JSONResponseWrapper.getResponseInstance(appusers);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper addAllData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JSONResponseWrapper getUserById(Integer id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ApplicationUser dbuser = usersDAO.getByID(id);
            UserDO response = modelMapper.map(dbuser, UserDO.class);
            return JSONResponseWrapper.getResponseInstance(response);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }
    }

    @Override
    public JSONResponseWrapper updateUser(UserDO usr) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ApplicationUser dbuser = modelMapper.map(usr, ApplicationUser.class);
            dbuser = usersDAO.addUser(dbuser);
            UserDO response = modelMapper.map(dbuser, UserDO.class);
            return JSONResponseWrapper.getResponseInstance(response);
        } catch (Exception e) {
            return JSONResponseWrapper.getErrorResponseInstance(
                    new JSONExceptionWrapper("Error", e));
        }

    }

}
