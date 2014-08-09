/*
 *  
 *  
 * 
 */

package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.AssumptionType;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface AssumptionTypeDAO {
    List<AssumptionType> listAll();
    AssumptionType saveOrUpdate(AssumptionType type);
    AssumptionType getById(Integer id);
    Boolean delete(Integer id);
}
