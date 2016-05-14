package twittbaguettes.controllers;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twittbaguettes.models.Message;
import twittbaguettes.repositories.MessageRepository;

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

    /**
     * List all messages
     */
    @RequestMapping(value = {"/messages", "/messages/"}, method = RequestMethod.GET)
    @ResponseBody
    public Page<Message> getMessages(
        @RequestParam(name = "page", required = false, 	defaultValue = "0") int page,
        @RequestParam(name = "perPage", required = false, defaultValue = "5") int perPage) {
        // Page par défaut 0, 10 messages par page
        PageRequest paginator = new PageRequest(page,perPage);
        return messageRepository.findAll(paginator);
    }

    /**
     * Retrieve one message by his id
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.GET)
    @ResponseBody
    public Message getMessage(@RequestParam(name = "id", required = true) String id) {
        Long identifier = new Long(id);
        if(messageRepository.exists(identifier)) {
            return messageRepository.findOne(identifier);
        } else {
            // TODO : Message Not Found Exception
        }
        return null;
    }

    /**
     * Create a message
     * Parameters in request body (JSON object)
     */
//    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.POST )
//    @ResponseBody
//    public Message create(@RequestBody(required = true) Message message) {
//        return messageRepository.save(message);
//    }
    
    /**
     * Create a message
     * Parameters in request param (urlencoded) 
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.POST )
    @ResponseBody
    public Message createMessage(
            @RequestParam(name= "content", required = true) String content,
            @RequestParam(name= "url", required = false, defaultValue = "") String url,
            @RequestParam(name= "img", required = false, defaultValue = "") String img) {
        Message message = new Message(content,img,url);
        return messageRepository.save(message);
    }

    /**
     * Edit a message
     * Parameters in request body (JSON MessageObject)
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.PUT )
    @ResponseBody
    public Message edit(@RequestBody(required = true) Message message) {
        return messageRepository.save(message);
    }

    /**
     * Edit a message
     * Params in request (urlencoded)
     */
//    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.PUT )
//    @ResponseBody
//    public Message editMessageByURL(
//            @RequestParam(name= "content", required = false) String content,
//            @RequestParam(name= "url", required = false) String url,
//            @RequestParam(name= "img", required = false) String img) {
//                Message message = new Message(content,url,img);
//        return messageRepository.save(message);
//    }

    /**
     * Delete a message
     * Parameters in request
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteMessageById(@RequestParam(name = "id", required = true) Long id) {
        try {
            if(messageRepository.exists(id)) {
               messageRepository.delete(id);
                return true;
            } else {
                // TODO : MessageNotFoundException
            }
        } catch(Exception e) {
            // TODO : Bad Request ?
        }
        return false;
    }
    
    /**
     * Delete a message
     * Parameters in request body (JSON Object)
     */
//    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.DELETE)
//    @ResponseBody
//    public boolean delete(@RequestBody(required = true) Message message) {
//        if(messageRepository.exists(message.getId())) {
//           messageRepository.delete(message);
//            return true;
//        } else {
//            // MessageNotFoundException
//        }
//        return false;
//    }

    /**
     * Retrieve one message by his id
     */
    @RequestMapping(value = {"/message/count","/message/count/"}, method = RequestMethod.GET)
    @ResponseBody
    public Long countMessages() {
        return messageRepository.count();
    }

    /**
     * Retrieve one message by his id
     */
    @RequestMapping(value = {"/message/exists","/message/exists/"}, method = RequestMethod.GET)
    @ResponseBody
    public boolean has(@RequestParam(name = "id", required = true) Long id) {
        return messageRepository.exists(id);
    }

}