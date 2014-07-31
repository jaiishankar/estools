package edu.aspen.capstone.estimation.relative.dao;

import edu.aspen.capstone.estimation.relative.entity.DevelopmentGroup;
import java.util.List;

/**
 *
 * @author jaiishankar
 */
public interface DevelopmentGroupDAO {

    DevelopmentGroup addGroup(DevelopmentGroup grp); //C

    List<DevelopmentGroup> listAll(); //R

    DevelopmentGroup getGroup(Integer id);

    DevelopmentGroup updateGroup(DevelopmentGroup grp); //U

    Boolean deleteGroup(Integer id); //D

}
