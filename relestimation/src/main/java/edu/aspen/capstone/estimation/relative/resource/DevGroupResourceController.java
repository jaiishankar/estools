package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.DevelopmentGroupDO;
import edu.aspen.capstone.estimation.relative.service.DevelopmentGroupService;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST controller class for all the Development Groups actions for users
 * @author jaiishankar
 */
@Controller
public class DevGroupResourceController {

    @Autowired
    private DevelopmentGroupService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/devgroups", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listAll() {
        return service.listAll();
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/devgroups/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getGroupById(@PathVariable Integer id) {
        return service.listGroup(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/devgroups/", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper addGroup(@RequestBody DevelopmentGroupDO grp) {
        return service.saveGroup(grp);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/devgroups", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper updateGroup(@RequestBody DevelopmentGroupDO grp) {
        return service.saveGroup(grp);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/devgroups/delete/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper deleteGroup(@PathVariable Integer id) {
        return service.deleteGroup(id);
    }
}
