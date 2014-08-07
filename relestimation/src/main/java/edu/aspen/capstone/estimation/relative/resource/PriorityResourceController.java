package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.PriorityDO;
import edu.aspen.capstone.estimation.relative.service.PriorityService;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Priority related REST URI's
 *
 * @author jaiishankar
 */
@Controller
public class PriorityResourceController {

    @Autowired
    private PriorityService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/priorities", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listAll() {
        System.out.println("Listing all the priorities");
        return service.listAll();
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/priorities/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getGroupById(@PathVariable Integer id) {
        System.out.println("Listing all the groups");
        return service.getPriority(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/priorities/", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper addGroup(@RequestBody PriorityDO pty) {
        return service.savePriority(pty);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/priorities/delete/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper deleteGroup(@PathVariable Integer id) {
        System.out.println("Inside delete for id: " + id);
        return service.deletePriority(id);
    }
}
