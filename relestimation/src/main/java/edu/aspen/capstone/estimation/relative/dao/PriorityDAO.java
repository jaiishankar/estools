package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.Priority;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface PriorityDAO {

    List<Priority> listAll();

    Priority getById(Integer id);

    Priority saveOrUpdate(Priority data);

    Boolean deletePriority(Integer id);

}
