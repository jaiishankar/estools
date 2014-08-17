package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Feature;

/**
 *
 * @author jaiishankar
 */
public interface FeatureDAO {

    Feature add(Feature fea);

    Feature update(Feature fea);

    Feature get(Integer fId);
    
    Boolean delete(Integer fId);

}
