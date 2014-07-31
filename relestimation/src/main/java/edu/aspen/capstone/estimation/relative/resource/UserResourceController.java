/*
 *  
 *  
 * 
 */
package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.domain.UserDO;
import edu.aspen.capstone.estimation.relative.service.UsersService;
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
public class UserResourceController {

    @Autowired
    private UsersService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/users", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper listAll() {
        return service.listAll();
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/users/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper getById(@PathVariable Integer id) {
        return service.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/users/logon", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper loginUser(@RequestBody UserDO usr) {
        return service.logon(usr);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/users/logout", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper logoutUser(@RequestBody UserDO usr) {
        return service.logout(usr);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/users/signup")
    public @ResponseBody
    JSONResponseWrapper signup(@RequestBody UserDO usr) {
        System.out.println("Signup service is called");
        return service.signup(usr);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/users", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper updateProfile(@RequestBody UserDO usr) {
        return service.signup(usr);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/users", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper removeUser(@RequestBody UserDO usr) {
        return service.signup(usr);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/users/activate/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper activate(@PathVariable Integer id) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/users/deactivate/{id}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper deactivate(@PathVariable Integer id) {
        return null;
    }
    
        @RequestMapping(method = RequestMethod.GET,
            value = "/users/check/{uname}", headers = "Accept=application/json")
    public @ResponseBody
    JSONResponseWrapper checkName(@PathVariable String uname) {
        return service.checkAvailablitiy(uname);
    }
}
