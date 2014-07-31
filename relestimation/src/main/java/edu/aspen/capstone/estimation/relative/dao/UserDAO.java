package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.ApplicationUser;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jaiishankar
 */
@Transactional 
public interface UserDAO {

    ApplicationUser addUser(ApplicationUser usr);

    boolean logout(ApplicationUser usr);
    
    ApplicationUser getByID(Integer id);

    ApplicationUser getByName(String name);

    JSONResponseWrapper deactiveUser(Long id);

    List<ApplicationUser> listAll();
    
    List<ApplicationUser> listAllLoggedUser();
    
    Object logon(ApplicationUser usr);
}
