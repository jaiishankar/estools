package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Businesscase;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface BusinesscaseDAO {

    Businesscase add(Businesscase bc);

    Businesscase update(Businesscase bc);

    Businesscase get(Integer bcId);

    Boolean delete(Integer bcId);
    
    Boolean deleteByFeature(Integer featureId);

    List<Businesscase> getAllCasesForFeature(Integer fId);
}
