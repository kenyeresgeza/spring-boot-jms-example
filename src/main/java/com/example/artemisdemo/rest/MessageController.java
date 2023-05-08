package com.example.artemisdemo.rest;

import com.example.artemisdemo.sender.SimpleMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MessageController {

    private SimpleMessageSender simpleMessageSender;

    public MessageController(SimpleMessageSender simpleMessageSender) {
        this.simpleMessageSender = simpleMessageSender;
    }

    @PostMapping("/send")
    public HttpEntity<String> send(@RequestBody String message){
        try{
            simpleMessageSender.send(message);

            return ResponseEntity.ok("Message sent: " + message);

        } catch (JmsException jmsException) {
            log.error("Could not send message", jmsException);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
