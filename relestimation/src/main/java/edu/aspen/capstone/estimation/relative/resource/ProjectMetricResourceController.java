/*
 *  
 *  
 * 
 */

package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.ProjectMetricDO;
import edu.aspen.capstone.estimation.relative.service.ProjectMetricService;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jaiishankar
 */
@Controller
public class ProjectMetricResourceController {
    @Autowired
    ProjectMetricService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/metrics/project/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listAll(@PathVariable Integer id) {
        return service.getMetricsForProject(id);
    }
    
    @RequestMapping(method = RequestMethod.GET,
            value = "/metrics/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getById(@PathVariable Integer id) {
        return service.get(id);
    }


    @RequestMapping(method = RequestMethod.POST,
            value = "/metrics", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper addMetric(@RequestBody ProjectMetricDO data) {
        return service.save(data);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/metrics/delete/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper deleteMetric(@PathVariable Integer id) {
        return service.delete(id);
    }
}
