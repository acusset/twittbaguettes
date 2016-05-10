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
public class MessageController {

    @RequestMapping("/create")
    @ResponseBody
    public Message create(String content) {
        Message message = null;
        try {
            message = new Message(content);
            return messageRepository.save(message);
        }
        catch (Exception ex) {
            return null;
        }
//        return null;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Message get(@RequestParam String id) {
        return messageRepository.findOne(new Long(id));
    }

    @Autowired
    private MessageRepository messageRepository;
}
