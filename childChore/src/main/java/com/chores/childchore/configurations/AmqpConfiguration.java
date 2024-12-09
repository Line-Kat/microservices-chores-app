package com.chores.childchore.configurations;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {

    // Name of the Exchanger
    @Bean
    public TopicExchange rewardTopicExchange(
            @Value("${amqp.exchange.name}") final String exchangeName
    ) {
        // The exchange object
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true) // The messages don't get lost if the broker goes down
                .build();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
