package com.bwgjoseph.springbootsleuthzipkintracing.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue myQueue() {
        return new Queue("sleuth-queue");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}