package com.example.artemisdemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Service
@Slf4j
public class SimpleConsumer {

    private JmsTemplate jmsTemplate;

    @Value("${jms.destination}")
    private String destination;

    @Autowired
    public SimpleConsumer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    // pull type consumer => thread blocking
    public void receiveMessage() throws JMSException {
        String msg = (String) jmsTemplate.receiveAndConvert(destination);

        log.info("Receive and convert message: " + msg);
    }

    // push type consumer => non thread blocking
    @JmsListener(destination = "${jms.destination}")
    public void receive(String message){
        log.info("Message received: " + message);
    }

}
