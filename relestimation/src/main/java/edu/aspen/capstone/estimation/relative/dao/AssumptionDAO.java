package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Assumption;
import java.util.List;

/**
 *
 * @author M447326
 */
public interface AssumptionDAO {

    List<Assumption> listAllByCase(Integer caseId);

    Assumption saveOrUpdate(Assumption type);

    Assumption getById(Integer id);

    Boolean delete(Integer id);
    
    Boolean deleteByCase(Integer caseId);
}
