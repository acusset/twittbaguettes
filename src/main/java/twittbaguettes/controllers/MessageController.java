package twittbaguettes.controllers;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twittbaguettes.errors.MessageNotFoundException;
import twittbaguettes.models.Message;
import twittbaguettes.models.User;
import twittbaguettes.repositories.MessageRepository;
import twittbaguettes.repositories.UserRepository;

import java.security.Principal;

/**
 * Message Controller define routes ands methods
 */
@Controller
public class MessageController {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /**
     * List all messages
     */
    @RequestMapping(value = {"/messages", "/messages/"}, method = RequestMethod.GET)
    @ResponseBody
    public Page<Message> getMessages(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") int perPage) {
        // Page par défaut 0, 10 messages par page
        PageRequest paginator = new PageRequest(page, perPage, new Sort(Sort.Direction.DESC, "createdAt"));
        return messageRepository.findAll(paginator);
    }

    /**
     * Retrieve one message by his id
     */
    @RequestMapping(value = {"/message/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public Message getMessage(@PathVariable("id") Long id) {
        Message message = messageRepository.findOne(id);
        if (message == null) {
            throw new MessageNotFoundException("Message id " + id + " was not fount in database");
        } else {
            return message;
        }
    }

    /**
     * Create a message
     * Parameters in request body (JSON object)
     */
    @RequestMapping(value = {"/message", "/message/"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> createMessage(@RequestBody Message message, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        if (user != null) {
            message.setCreatedAt(DateTime.now());
            message.setUser(user);
            Long id = messageRepository.save(message).getId();
            return new ResponseEntity<>(messageRepository.findOne(id), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new Message(), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Edit a message
     * Parameters in request body (JSON MessageObject)
     */
    @RequestMapping(value = {"/message/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Message> edit(@PathVariable("id") Long id, @RequestBody Message message, Principal principal) {
        if (messageRepository.exists(id)) {
            User connectedUser = userRepository.findByUsername(principal.getName());
            Message oldMessage = messageRepository.findOne(id);
            if (oldMessage.isAuthor(connectedUser) || connectedUser.isAdmin()) {
                oldMessage.setContent(message.getContent());
                oldMessage.setUrl(message.getUrl());
                oldMessage.setImg(message.getImg());
                oldMessage.setId(oldMessage.getId());
                oldMessage.setUpdatedAt(DateTime.now());
                oldMessage.setUser(connectedUser);
                messageRepository.save(oldMessage);
                return new ResponseEntity<>(oldMessage, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Message(), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(new Message(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a message
     * Parameters in request
     */
    @RequestMapping(value = {"/message/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Message> deleteMessageById(@PathVariable("id") Long id, Principal principal) {
        if (messageRepository.exists(id)) {
            User connectedUser = userRepository.findByUsername(principal.getName());
            Message message = messageRepository.findOne(id);
            if (message.isAuthor(connectedUser)) {
                messageRepository.delete(id);
                return new ResponseEntity<>(new Message(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Message(), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(new Message(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieve messages count
     */
    @RequestMapping(value = {"/message/count", "/message/count/"}, method = RequestMethod.GET)
    @ResponseBody
    public Long countMessages() {
        return messageRepository.count();
    }

    /**
     * Check if message exists
     */
    @RequestMapping(value = {"/message/exists/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public boolean has(@PathVariable("id") Long id) {
        return messageRepository.exists(id);
    }

}