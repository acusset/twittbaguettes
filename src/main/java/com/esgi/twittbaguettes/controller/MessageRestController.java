package com.esgi.twittbaguettes.controller;

import com.esgi.twittbaguettes.domain.Message;
import com.esgi.twittbaguettes.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * springbaguettes
 *
 * @author Antoine on 07/05/2016.
 *         -- Description du fichier --
 */
@RestController
@RequestMapping("/messages")
public class MessageRestController {

//    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Message sayHello(@RequestParam(value = "name", required = false, defaultValue = "Antoine") String name) {
        return new Message(name, "Cusset");
    }

    @RequestMapping(method=RequestMethod.POST)
    public Message create(@RequestBody Message message) {
        return message;
//        return messageRepository.save(message);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="{id}")
    public void delete(@PathVariable String id) {
        Message message = messageRepository.findOne(Long.parseLong(id));
        messageRepository.delete(message);
    }

    @RequestMapping(method=RequestMethod.PUT, value="{id}")
    public Message update(@PathVariable String id, @RequestBody Message message) {

        Message updated = messageRepository.findOne(Long.parseLong(id));
        updated.setFirstName(message.getFirstName());
        updated.setLastName(message.getLastName());

        return messageRepository.save(updated);
    }
    
}