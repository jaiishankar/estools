/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Sizing;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface SizingDAO {

    List<Sizing> listAll();

    Sizing saveOrUpdate(Sizing size);

    Boolean delete(Integer id);

    Sizing getSizingById(Integer id);
}
