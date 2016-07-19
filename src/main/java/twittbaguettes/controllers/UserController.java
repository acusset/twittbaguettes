package twittbaguettes.controllers;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twittbaguettes.models.Role;
import twittbaguettes.models.User;
import twittbaguettes.repositories.RoleRepository;
import twittbaguettes.repositories.UserRepository;

import java.security.Principal;

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
    @Autowired
    private RoleRepository roleRepository;

    /**
     * List all users
     */
    @RequestMapping(value = {"/users", "/users/"}, method = RequestMethod.GET)
    @ResponseBody
    public Page<User> getUsers(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "5") int perPage) {
        // Page par défaut 0, 5 users par page
        PageRequest paginator = new PageRequest(page, perPage);
        return userRepository.findAll(paginator);
    }

    /**
     * Retrieve one user by his id
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    /**
     * Return current logged in user
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> currentUserName(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    /**
     * Create an user
     * Parameters in request body (JSON object)
     */
    @RequestMapping(value = {"/user", "/user/"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> create(@RequestBody User user) {
        user.setCreatedAt(DateTime.now());
        user.generateApiKey();
//        Role userROle = roleRepository.f
        user = userRepository.save(user);
        // Ajoute un rôle par défaut
//        roleRepository.save(new Role(user, Role.ROLE_USER));
        User newUser = userRepository.findOne(user.getId());
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Edit an user
     * Parameters in request body (JSON UserObject)
     */
    @RequestMapping(value = {"/user/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<User> edit(@PathVariable("id") Long id, @RequestBody User user) {

        User currentUser = userRepository.findById(id);
        if (user != null) {
            currentUser.setPassword(user.getPassword());
            currentUser.setUsername(user.getUsername());
            currentUser.setEmail(user.getEmail());
            userRepository.save(currentUser);
            currentUser.setUpdatedAt(DateTime.now());
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete an user
     * Parameters in request
     */
    @RequestMapping(value = {"/user/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<User> deleteUserById(@PathVariable("id") Long id) {
        if (userRepository.exists(id)) {
            Role role = roleRepository.findById(id);
            roleRepository.delete(role.getId());
            userRepository.delete(id);
            return new ResponseEntity<>(new User(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieve users count
     */
    @RequestMapping(value = {"/users/count", "/users/count/"}, method = RequestMethod.GET)
    @ResponseBody
    public Long count() {
        return userRepository.count();
    }

    /**
     * Check if user exists
     */
    @RequestMapping(value = {"/user/exists/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public boolean has(@PathVariable Long id) {
        return userRepository.exists(id);
    }
}