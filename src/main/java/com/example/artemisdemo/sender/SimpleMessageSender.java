package com.example.artemisdemo.sender;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
@Slf4j
public class SimpleMessageSender {

    private JmsTemplate jmsTemplate;

    public static final String QUEUE = "DEMO_QUEUE";

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue(QUEUE);
    }

    public SimpleMessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Value("${jms.destination}")
    private String destination;

    public void send(String message) throws JmsException {
        jmsTemplate.convertAndSend(destination, message);

        log.info("Message sent: " + message);
    }

}
