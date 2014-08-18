/*
 *  
 *  
 * 
 */

package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.FeatureDO;
import edu.aspen.capstone.estimation.relative.service.FeatureService;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jaiishankar
 */
@Controller
public class FeatureResourceController {
    
    @Autowired
    private FeatureService service;
    
    @RequestMapping(method = RequestMethod.GET,
            value = "/features/project/{id}", headers = "Accept=application/json")
    public @ResponseBody JSONResponseWrapper getFeatureByProject(@PathVariable Integer id){
        return service.getFeatursByProject(id);
    }
    
    @RequestMapping(method = RequestMethod.POST,
            value = "/features", headers = "Accept=application/json")
    public @ResponseBody JSONResponseWrapper saveOrUpdate(@RequestBody FeatureDO feature){
        return service.update(feature);
    }
    
    @RequestMapping(method = RequestMethod.POST,
            value = "/features/delete/{id}", headers = "Accept=application/json")
    public @ResponseBody JSONResponseWrapper delete(@PathVariable Integer id){
        return service.delete(id);
    }
    
     @RequestMapping(method = RequestMethod.GET,
            value = "/features/{id}", headers = "Accept=application/json")
     public @ResponseBody JSONResponseWrapper getFeature(@PathVariable Integer id){
     return service.get(id);
     }
    
}
