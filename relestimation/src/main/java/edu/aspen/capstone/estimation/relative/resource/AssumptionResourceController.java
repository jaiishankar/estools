package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.AssumptionDO;
import edu.aspen.capstone.estimation.relative.service.AssumptionService;
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
 * @author JaiShankar
 */
@Controller
public class AssumptionResourceController {

    @Autowired
    private AssumptionService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/assumption/case/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getByCaseId(@PathVariable Integer id) {
        return service.listAllByCase(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/assumption/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getById(@PathVariable Integer id) {
        System.out.println("Listing all the groups");
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/assumption/", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper update(@RequestBody AssumptionDO bc) {
        return service.saveOrUpdate(bc);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/assumption/delete/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper deleteAssumption(@PathVariable Integer id) {
        return service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/devgroups/delete/case/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper deleteByCase(@PathVariable Integer id) {
        return service.deleteByCase(id);
    }
}
