package twittbaguettes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import twittbaguettes.models.Message;
import twittbaguettes.models.MessageRepository;

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
    public List<Message> getMessages(
        @RequestParam(name = "page", required = false, 	defaultValue = 0) int page
        @RequestParam(name = "perPage", required = false, defaultValue = 5) int perPage) {
        // Page par défaut 0, 10 messages par page
        return messageRepository.findAll(new Pageable(page,perPage));
    }

    /**
     * Retrieve one message by his id
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.GET)
    @ResponseBody
    public Message getMessage(@RequestParam(name = "id", required = true) String id) {
        return messageRepository.findOne(new Long(id));
    }

    /**
     * Create a message
     * Parameters in request body (JSON object)
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.POST )
    @ResponseBody
    public Message create(@RequestBody(required = true) Message message) {
        return messageRepository.save(message);
    }
    
    /**
     * Create a message
     * Parameters in request param (urlencoded) 
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.POST )
    @ResponseBody
    public Message createMessage(
            @RequestParam(name= "content", required = true) String content,
            @RequestParam(name= "url", required = false) String url,
            @RequestParam(name= "img", required = false) String img) {
        return messageRepository.save(message);
    }

    /**
     * Edit a message
     * Parameters in request body (JSON MessageObject)
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.PUT )
    @ResponseBody
    public Message editMessage(@RequestBody(required = true) Message message) {
        return messageRepository.save(message);
    }

    /**
     * Edit a message
     * Params in request (urlencoded)
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.PUT )
    @ResponseBody
    public Message editMessageByURL(
            @RequestParam(name= "content", required = false) String content,
            @RequestParam(name= "url", required = false) String url,
            @RequestParam(name= "img", required = false) String img) {
                Message message = new Message(content,url,img);
        return messageRepository.save(message);
    }

    /**
     * Delete a message
     * Parameters in request
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteMessageById(@RequestParam(name = "id", required = true) String id) {
        try {
            Long id = Long.parseLong(id);
            if(messageRepository.exists(id) {
               return messageRepository.delete(id);
            } else {
                // TODO : MessageNotFoundException
            }
        } catch(Exception e) {
            // TODO : Bad Request ?
        }
    }
    
    /**
     * Delete a message
     * Parameters in request body (JSON Object)
     */
    @RequestMapping(value = {"/message","/message/"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteMessage(@RequestBody(required = true) Message messagae) {
        if(messageRepository.exists(messagae) {
           return messageRepository.delete(message);
        } else {
            // MessageNotFoundException
        }
    }

    /**
     * Retrieve one message by his id
     */
    @RequestMapping(value = {"/message/count","/message/count/"}, method = RequestMethod.GET)
    @ResponseBody
    public Message countMessages() {
        return messageRepository.count();
    }

    /**
     * Retrieve one message by his id
     */
    @RequestMapping(value = {"/message/exists","/message/exists/"}, method = RequestMethod.GET)
    @ResponseBody
    public Message hasMessage(@RequestParam(name = "id", required = true) String id) {
        return messageRepository.exisits(new Long(id));
    }

}
