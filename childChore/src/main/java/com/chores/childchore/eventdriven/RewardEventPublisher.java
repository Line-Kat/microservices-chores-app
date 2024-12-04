package com.chores.childchore.eventdriven;

import com.chores.childchore.model.ChildChore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void publishRewardEvent(List<ChildChore> listOfCompletedChores) {
        // build the message/event (choreUuid? childUuid?)
        RewardEvent event = buildEvent(listOfCompletedChores);

        // decide on routing
        String routingKey = "chore.completed";

        // send the thing
        amqpTemplate.convertAndSend(exchangeName, routingKey, event);
    }

    private RewardEvent buildEvent(List<ChildChore> listOfCompletedChores) {
        return new RewardEvent(listOfCompletedChores);
    }
}
