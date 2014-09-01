package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.ProjectFeatureSizingDO;
import edu.aspen.capstone.estimation.relative.service.ProjectFeatureSizingService;
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
public class ProjectFeatureSizingResourceController {

    @Autowired
    ProjectFeatureSizingService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/mappings/project/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getByProject(@PathVariable Integer id) {
        return service.getAllByProject(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/mappings/feature/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getByFeature(@PathVariable Integer id) {
        return service.getAllByFeature(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/mappings/delete/{id}", headers = "Accept=application/json")
    public @ResponseBody JSONResponseWrapper delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/mappings/", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper update(@RequestBody ProjectFeatureSizingDO bc) {
        return service.save(bc);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/mappings/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getMappings(@PathVariable Integer id) {
        return service.get(id);
    }

}
