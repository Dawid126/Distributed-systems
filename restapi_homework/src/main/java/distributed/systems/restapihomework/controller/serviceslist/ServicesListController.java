package distributed.systems.restapihomework.controller.serviceslist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ServicesListController {

    public ServicesListController() {

    }

    @RequestMapping("/")
    public String servicesList() {
        return "services_list";
    }

}
