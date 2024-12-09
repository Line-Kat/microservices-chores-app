package com.chores.childchore.eventdriven;

import com.chores.childchore.DTO.ChildChoreDateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RewardEventPublisher {

    private final AmqpTemplate amqpTemplate;
    private final String exchangeName;

    public RewardEventPublisher(
            final AmqpTemplate amqpTemplate,
            @Value("${amqp.exchange.name}") final String exchangeName) {

        this.amqpTemplate = amqpTemplate;
        this.exchangeName = exchangeName;
    }

    // Method to send an event to rabbitMQ
    public void publishRewardEvent(ChildChoreDateDTO childChoreDateDTO) {
        // Build the event
        RewardEvent event = buildEvent(childChoreDateDTO);

        String routingKey = "chore.completed";

        // Send the event
        amqpTemplate.convertAndSend(exchangeName, routingKey, event);
    }

    // Method to build the event
    private RewardEvent buildEvent(ChildChoreDateDTO childChoreDateDTO) {
        return new RewardEvent(childChoreDateDTO);
    }
}
