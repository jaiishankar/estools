package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.ProjectContributorsDO;
import edu.aspen.capstone.estimation.relative.service.ProjectContributorsService;
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
public class ProjectContributorsReourceController {

    @Autowired
    ProjectContributorsService service;

    @RequestMapping(method = RequestMethod.POST,
            value = "/contributors/", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper update(@RequestBody ProjectContributorsDO bc) {
        return service.save(bc);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/contributors/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper get(@PathVariable Integer id) {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/contributors/delete/{id}", headers = "Accept=application/json")
    public @ResponseBody JSONResponseWrapper delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/contributors/user/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getCasesForUser(@PathVariable Integer id) {
        return service.getByUsers(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/contributors/project/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getCasesForProject(@PathVariable Integer id) {
        return service.getByProject(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/contributors/group/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getCasesForGroup(@PathVariable Integer id) {
        return service.getByGroup(id);
    }

}
