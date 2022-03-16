package com.rabbitmq.delay.demo;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import static com.rabbitmq.delay.demo.RabbitMQConfig.DEAD_LETTER_QUEUE_NAME;

@Slf4j
@Component
public class DeadLetterQueueConsumer {
    @RabbitListener(queues = DEAD_LETTER_QUEUE_NAME)
    public void receive(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{}，死信队列收到消息：{}", new Date(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
