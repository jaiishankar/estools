package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.BusinesscaseDO;
import edu.aspen.capstone.estimation.relative.service.BusinesscaseService;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Rest controller for all the Business cases.
 * @author jaiishankar
 */
@Controller
public class BusinesscaseResourceController {

    @Autowired
    BusinesscaseService service;

    @RequestMapping(method = RequestMethod.POST,
            value = "/cases/", headers = "Accept=application/json")
    public @ResponseBody JSONResponseWrapper update(@RequestBody BusinesscaseDO bc) {
        return service.update(bc);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/cases/{id}", headers = "Accept=application/json")
    public @ResponseBody JSONResponseWrapper get(@PathVariable Integer id) {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/cases/delete/{id}", headers = "Accept=application/json")
    public JSONResponseWrapper delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/cases/feature/{id}", headers = "Accept=application/json")
    public @ResponseBody JSONResponseWrapper getCasesForFeature(@PathVariable Integer fId) {
        return service.getCasesForFeature(fId);
    }
    
    
}
