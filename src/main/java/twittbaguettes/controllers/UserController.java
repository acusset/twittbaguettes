package twittbaguettes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twittbaguettes.models.User;
import twittbaguettes.repositories.UserRepository;

/**
 * User Controller define routes ands methods
 */
@Controller
public class UserController {

    /**
     * Dependancy Injection
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * List all users
     */
    @RequestMapping(value = {"/users", "/users/"}, method = RequestMethod.GET)
    @ResponseBody
    public Page<User> getUsers(
            @RequestParam(name = "page", required = false, 	defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "5") int perPage) {
        // Page par défaut 0, 10 users par page
        PageRequest paginator = new PageRequest(page,perPage);
        return userRepository.findAll(paginator);
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
     * Create a user
     * Parameters in request body (JSON object)
     */
//    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.POST )
//    @ResponseBody
//    public User create(@RequestBody(required = true) User user) {
//        return userRepository.save(user);
//    }

    /**
     * Create a user
     * Parameters in request param (urlencoded) 
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.POST )
    @ResponseBody
    public User createUser(
            @RequestParam(name= "username", required = true) String username,
            @RequestParam(name= "email", required = true) String email,
            @RequestParam(name= "password", required = true) String password) {
        User user = new User(username,email,password);
        return userRepository.save(user);
    }

    /**
     * Edit a user
     * Parameters in request body (JSON UserObject)
     */
//    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.PUT )
//    @ResponseBody
//    public User edit(@RequestBody(required = true) User user) {
//        return userRepository.save(user);
//    }

    /**
     * Edit a user
     * Params in request (urlencoded)
     * TODO : edit au lieu de new
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.PUT )
    @ResponseBody
    public User editUserByURL(
            @RequestParam(name= "username", required = false) String username,
            @RequestParam(name= "email", required = false) String email,
            @RequestParam(name= "password", required = false) String password) {
        User user = new User(username,email,password);
        return userRepository.save(user);
    }

    /**
     * Delete a user
     * Parameters in request
     */
    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteUserById(@RequestParam(name = "id", required = true) Long id) {
        try {
            if(userRepository.exists(id)) {
                userRepository.delete(id);
                return true;
            } else {
                // TODO : UserNotFoundException
            }
        } catch(Exception e) {
            // TODO : Bad Request ?
        }
        return false;
    }

    /**
     * Delete a user
     * Parameters in request body (JSON Object)
     */
//    @RequestMapping(value = {"/user","/user/"}, method = RequestMethod.DELETE)
//    @ResponseBody
//    public boolean delete(@RequestBody(required = true) User user) {
//        if(userRepository.exists(user.getId())) {
//            userRepository.delete(user);
//            return true;
//        } else {
//            // UserNotFoundException
//        }
//        return false;
//    }

    /**
     * Retrieve one user by his id
     */
    @RequestMapping(value = {"/users/count","/users/count/"}, method = RequestMethod.GET)
    @ResponseBody
    public Long count() {
        return userRepository.count();
    }

    /**
     * Retrieve one user by his id
     */
    @RequestMapping(value = {"/user/exists","/user/exists/"}, method = RequestMethod.GET)
    @ResponseBody
    public boolean has(@RequestParam(name = "id", required = true) Long id) {
        return userRepository.exists(id);
    }
}