package com.chores.user.eventdriven;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    // method will send a json
    public void publishRewardEvent(RewardEvent rewardEvent) {
        // build the message/event (choreUuid? childUuid?)

        // decide on routing

        // send the thing
        amqpTemplate.convertAndSend(rewardEvent);
    }
}
