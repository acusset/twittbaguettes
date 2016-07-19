package twittbaguettes.controllers;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twittbaguettes.models.Message;
import twittbaguettes.repositories.MessageRepository;
import twittbaguettes.repositories.UserRepository;

/**
 * Message Controller define routes ands methods
 */
@Controller
public class MessageController {

    /**
     * Dependancy Injection
     */
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * List all messages
     */
    @RequestMapping(value = {"/messages", "/messages/"}, method = RequestMethod.GET)
    @ResponseBody
    public Page<Message> getMessages(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") int perPage) {
        // Page par défaut 0, 10 messages par page
        PageRequest paginator = new PageRequest(page, perPage);
        return messageRepository.findAll(paginator);
    }

    /**
     * Retrieve one message by his id
     */
    @RequestMapping(value = {"/message/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Message> getMessage(@PathVariable("id") Long id) {
        Message message = messageRepository.findOne(id);
        if (message == null) {
            return new ResponseEntity<>(new Message(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    /**
     * Create a message
     * Parameters in request body (JSON object)
     */
    @RequestMapping(value = {"/message", "/message/"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        message.setCreatedAt(DateTime.now());
        message.setUser(userRepository.findByUsername("User"));
        Long id = messageRepository.save(message).getId();

        return new ResponseEntity<>(messageRepository.findOne(id), HttpStatus.OK);
    }

    /**
     * Edit a message
     * Parameters in request body (JSON MessageObject)
     */
    @RequestMapping(value = {"/message/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Message> edit(@PathVariable("id") Long id, @RequestBody Message message) {
        if (messageRepository.exists(id)) {
            Message currentMessage = messageRepository.findOne(id);
            currentMessage.setContent(message.getContent());
            currentMessage.setUrl(message.getUrl());
            currentMessage.setImg(message.getImg());
            currentMessage.setUpdatedAt(DateTime.now());
            messageRepository.save(currentMessage);
            return new ResponseEntity<>(currentMessage, HttpStatus.OK);
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
    public ResponseEntity<Message> deleteMessageById(@PathVariable("id") Long id) {
        if(messageRepository.exists(id)) {
            messageRepository.delete(id);
            return new  ResponseEntity<>(new Message(), HttpStatus.OK);
        } else {
            return new  ResponseEntity<>(new Message(), HttpStatus.NOT_FOUND);
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