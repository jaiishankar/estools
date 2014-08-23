package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.GroupUserDO;
import edu.aspen.capstone.estimation.relative.domain.UserGroupDO;
import edu.aspen.capstone.estimation.relative.service.UserGroupService;
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
public class UserGroupResourceController {

    @Autowired
    UserGroupService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/usergroups/user/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listGroupsByUserId(@PathVariable Integer id) {
        return service.getGroupsForUser(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/usergroups/group/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listUsersByGroupId(@PathVariable Integer id) {
        return service.getUsersForGroup(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/usergroups/user/groups/", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper updateGroupForUsers(@RequestBody UserGroupDO project) {
        return service.updateAssociations(project);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/usergroups/group/users", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper updateUsersForGroups(@RequestBody GroupUserDO data) {
        return service.updateAssociations(data);
    }
}
