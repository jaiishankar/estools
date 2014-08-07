package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.SizingDO;
import edu.aspen.capstone.estimation.relative.service.SizingService;
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
public class SizingResourceController {

    @Autowired
    SizingService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/sizing", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listAll() {
        return service.listAll();
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/sizing", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper addNewSizing(@RequestBody SizingDO size) {
        return service.saveOrUpdate(size);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/sizing/delete/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper deleteSizing(@PathVariable Integer id) {
        return service.delete(id);
    }
}
