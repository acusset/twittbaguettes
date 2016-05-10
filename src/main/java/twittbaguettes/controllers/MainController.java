package twittbaguettes.controllers;

/**
 * Twittbaguettes
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World";
    }

}
