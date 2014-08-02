/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.ProjectDO;
import edu.aspen.capstone.estimation.relative.service.ProjectService;
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
public class ProjectResourceController {

    @Autowired
    private ProjectService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/projects/user/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listProjectByUserId(@PathVariable Integer id) {
        return service.getProjectsByUserId(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/projects/owner/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listProjectByOwnerId(@PathVariable Integer id) {
        return service.getProjectsByOwner(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/projects/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listProjectById(@PathVariable Integer id) {
        return service.getProjectsById(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/projects", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listAll() {
        System.out.println("Listing all the projects");
        return service.getAllProjects();
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/projects", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper saveProject(@RequestBody ProjectDO prj) {
        return service.saveOrUpdateProject(prj);
    }

}
