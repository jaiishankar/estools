package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.AssumptionTypeDO;
import edu.aspen.capstone.estimation.relative.service.AssumptionTypeService;
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
public class AssumptionTypeResourceController {
    @Autowired
    private AssumptionTypeService service;
    
    @RequestMapping(method = RequestMethod.GET,
            value = "/assumptiontypes", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listAll() {
        return service.getAll();
    }
    
    @RequestMapping(method = RequestMethod.GET,
            value = "/assumptiontypes/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getById(@PathVariable Integer id) {
        System.out.println("Listing all the groups");
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/assumptiontypes", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper addGroup(@RequestBody AssumptionTypeDO typ) {
        return service.save(typ);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/assumptiontypes", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper updateGroup(@RequestBody AssumptionTypeDO typ) {
        return service.save(typ);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/assumptiontypes/delete/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper deleteGroup(@PathVariable Integer id) {
        return service.delete(id);
    }
}
