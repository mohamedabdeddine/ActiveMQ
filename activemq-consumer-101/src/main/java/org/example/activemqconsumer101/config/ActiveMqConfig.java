package org.example.activemqconsumer101.config;

import jakarta.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class ActiveMqConfig {
    @Value("${spring.activemq.broker.url}")
    private String brokerUrl;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("javatute-queue");
    }
}
