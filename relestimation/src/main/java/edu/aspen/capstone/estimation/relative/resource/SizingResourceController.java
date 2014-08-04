package edu.aspen.capstone.estimation.relative.resource;

import edu.aspen.capstone.estimation.relative.service.SizingService;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        System.out.println("Listing all the sizings");
        return service.listAll();
    }
}
