package twittbaguettes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twittbaguettes.models.Message;
import twittbaguettes.models.MessageRepository;

/**
 * Twittbaguettes
 */
@Controller
public class UserController {
    
    /**
     * Create Method (POST)
     */
    @RequestMapping('/create', RequestMethod.POST)
    public User create(User user) {
            
    }
    
    /**
     * Update Method (PUT/PATCH)
     */
     
    /**
     * Read Methods
     * (id / username / email)
     * Multiple views possibles
     */
     
    /**
     * Delete Method (DELETE)
     */
     
    /**
     * Autres méthodes count etc.
     */
}