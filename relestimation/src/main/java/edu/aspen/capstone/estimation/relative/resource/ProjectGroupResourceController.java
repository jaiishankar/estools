package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.ProjectGroupDO;
import edu.aspen.capstone.estimation.relative.service.ProjectGroupService;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jaiishankar
 */
@Controller
public class ProjectGroupResourceController {

    @Autowired
    ProjectGroupService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/projectgroups/project/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listProjectByProjectId(@PathVariable Integer id) {
        return service.getGroupsForProject(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/projectgroups/project/groups/", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper lupdateGroupsForProjects(@PathVariable ProjectGroupDO project) {
        return service.updateAssociations(project);
    }

}
