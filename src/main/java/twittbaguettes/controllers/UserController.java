package twittbaguettes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twittbaguettes.models.User;
import twittbaguettes.models.UserRepository;

/**
 * User Controller
 */
@Controller
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * List all users
     */
    @RequestMapping(value = {"/users", "/users/"}, method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers(
        @RequestParam(name = "page", required = false, 	defaultValue = 0) int page
        @RequestParam(name = "perPage", required = false, defaultValue = 5) int perPage) {
        // Page par défaut 0, 10 users par page
        return userRepository.findAll(new Pageable(page,perPage));
    }

    /**
     * Retrieve one user by his id
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@RequestParam(name = "id", required = true) String id) {
        return userRepository.findOne(new Long(id));
    }

    /**
     * Create an user
     * Parameters in request body (JSON object)
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.POST )
    @ResponseBody
    public User create(@RequestBody(required = true) User user) {
        return userRepository.save(user);
    }
    
    /**
     * Create an user
     * Parameters in request param (urlencoded) 
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.POST )
    @ResponseBody
    public User createUser(
            @RequestParam(name= "username", required = true) String content,
            @RequestParam(name= "email", required = false) String url,
            @RequestParam(name= "password", required = false) String img) {
                User user = new User(username, password, email, 0)
        return userRepository.save(user);
    }

    /**
     * Edit an user
     * Parameters in request body (JSON Object)
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.PUT )
    @ResponseBody
    public User editUser(@RequestBody(required = true) User user) {
        return userRepository.save(user);
    }

    /**
     * Edit an user
     * Params in request (urlencoded)
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.PUT )
    @ResponseBody
    public User editUserByURL(
            @RequestParam(name= "content", required = false) String content,
            @RequestParam(name= "url", required = false) String url,
            @RequestParam(name= "img", required = false) String img) {
        return userRepository.save(user);
    }

    /**
     * Delete an user
     * Parameters in request
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.DELETE)
    @ResponseBody
    public User deleteUserById(@RequestParam(name = "id", required = true) String id) {
        try {
            Long id = Long.parseLong(id);
            if(userRepository.exists(id) {
               return userRepository.delete(id);
            } else {
                // TODO : UserNotFoundException
            }
        } catch(Exception e) {
            // TODO : Bad Request ?
        }
    }
    
    /**
     * Delete an user
     * Parameters in request body (JSON Object)
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.DELETE)
    @ResponseBody
    public User deleteUser(@RequestBody(required = true) User messagae) {
        if(userRepository.exists(messagae) {
           return userRepository.delete(user);
        } else {
            // UserNotFoundException
        }
    }

    /**
     * Retrieve one user by his id
     */
    @RequestMapping(value = {"/user/count","/user/count/"}, method = RequestMethod.GET)
    @ResponseBody
    public User countUsers() {
        return userRepository.count();
    }

    /**
     * Retrieve one user by his id
     */
    @RequestMapping(value = {"/user/exists","/user/exists/"}, method = RequestMethod.GET)
    @ResponseBody
    public User hasUser(@RequestParam(name = "id", required = true) String id) {
        return userRepository.exisits(new Long(id));
    }
}