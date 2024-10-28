package com.chores.user.eventdriven;

import com.chores.user.model.Child;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    // method will send a json
    public void publishRewardEvent(List<UUID> choreUuid, Child child) {
        // build the message/event (choreUuid? childUuid?)
        RewardEvent event = buildEvent(choreUuid, child);

        // decide on routing
        String routingKey = "chore.completed";

        // send the thing
        log.info("Publishing reward event/rabbit message: " + event);
        amqpTemplate.convertAndSend(exchangeName, routingKey, event);
    }

    private RewardEvent buildEvent(List<UUID> choreUuid, Child child) {
        return new RewardEvent(
                child.getChildUuid(),
                choreUuid);
    }
}
