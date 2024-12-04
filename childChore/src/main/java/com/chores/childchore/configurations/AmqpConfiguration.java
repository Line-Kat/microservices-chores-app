package com.chores.childchore.configurations;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
    // because for the user to publish something somewhere, it needs to know the name of the exchange

    @Bean
    public TopicExchange rewardTopicExchange(
            @Value("${amqp.exchange.name}") final String exchangeName
    ) {
        // the exchange object
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true) // messages don't get lost if the broker goes down
                .build();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
