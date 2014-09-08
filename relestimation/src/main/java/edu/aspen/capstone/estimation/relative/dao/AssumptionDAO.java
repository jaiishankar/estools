package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Assumption;
import java.util.List;

/**
 * <h4>AssumptionDAO</h4>
 * Primary Interface for all the Assumption modules which will be used by 
 * all the service classes to act on the data.
 * @see AssumptionDAOImpl
 * @author M447326
 */
public interface AssumptionDAO {
    /**
     * Lists all the Assumptions based on business case id
     * @param caseId
     * @return 
     */
    List<Assumption> listAllByCase(Integer caseId);

    /**
     * Saves or updates Assumption data
     * @param type
     * @return 
     */
    Assumption saveOrUpdate(Assumption type);
    /**
     * Gets the Assumption data by its ID
     * @param id
     * @return 
     */
    Assumption getById(Integer id);

    /**
     * Deletes the Assumption data by ID
     * @param id
     * @return 
     */
    Boolean delete(Integer id);
    
    /**
     * Deletes all the assumptions for given case id
     * @param caseId
     * @return 
     */
    Boolean deleteByCase(Integer caseId);
}
