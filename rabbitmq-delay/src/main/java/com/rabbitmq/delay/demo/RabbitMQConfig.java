package com.rabbitmq.delay.demo;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    public static final String DELAY_EXCHANGE_NAME = "delay.queue.demo.exchange";
    public static final String DELAY_QUEUE_ROUTING_KEY = "delay.queue.demo.queue.routingKey";
    public static final String DEAD_LETTER_QUEUE_NAME = "delay.queue.demo.deadLetter.queue";
    private static final Integer TTL = 10000;
    private static final String DELAY_QUEUE_NAME = "delay.queue.demo.queue";
    private static final String DEAD_LETTER_EXCHANGE = "delay.queue.demo.deadLetter.exchange";
    private static final String DEAD_LETTER_QUEUE_ROUTING_KEY = "delay.queue.demo.deadLetter.routingKey";

    /**
     * 声明延时Exchange
     */
    @Bean("delayExchange")
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    /**
     * 声明死信Exchange
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明延时队列，并绑定到对应的死信Exchange
     */
    @Bean("delayQueue")
    public Queue delayQueue() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange 声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key 声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_ROUTING_KEY);
        // x-message-ttl 声明队列的TTL
        args.put("x-message-ttl", TTL);
        return QueueBuilder.durable(DELAY_QUEUE_NAME).withArguments(args).build();
    }

    /**
     * 声明延时队列绑定关系
     */
    @Bean
    public Binding delayBinding(@Qualifier("delayQueue") Queue queue,
                                @Qualifier("delayExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE_ROUTING_KEY);
    }

    /**
     * 声明死信队列，用于接收延时处理的消息
     */
    @Bean("deadLetterQueue")
    public Queue deadLetterQueueA() {
        return new Queue(DEAD_LETTER_QUEUE_NAME);
    }

    /**
     * 声明死信队列绑定关系
     */
    @Bean
    public Binding deadLetterBinding(@Qualifier("deadLetterQueue") Queue queue,
                                     @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_ROUTING_KEY);
    }
}
