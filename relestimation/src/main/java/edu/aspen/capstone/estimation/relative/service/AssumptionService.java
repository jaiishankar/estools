/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.AssumptionDO;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author M447326
 */
public interface AssumptionService {

    JSONResponseWrapper listAllByCase(Integer caseId);

    JSONResponseWrapper saveOrUpdate(AssumptionDO type);

    JSONResponseWrapper getById(Integer id);

    JSONResponseWrapper delete(Integer id);

    JSONResponseWrapper deleteByCase(Integer caseId);
}
