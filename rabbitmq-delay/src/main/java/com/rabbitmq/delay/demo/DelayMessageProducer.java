package com.rabbitmq.delay.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.rabbitmq.delay.demo.RabbitMQConfig.DELAY_EXCHANGE_NAME;
import static com.rabbitmq.delay.demo.RabbitMQConfig.DELAY_QUEUE_ROUTING_KEY;

@Slf4j
@Component
public class DelayMessageProducer implements CommandLineRunner {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final AtomicLong counter = new AtomicLong(0);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
        // 每3秒产生一条消息，应用启动后开始执行
        executor.scheduleAtFixedRate(this::sendMsg, 1, 3, TimeUnit.SECONDS);
    }

    public void sendMsg() {
        String msg = "test msg " + counter.incrementAndGet();
        if (counter.get() < 11) {
            log.info("当前时间：{}，发送消息：{}", new Date(), msg);
            rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUE_ROUTING_KEY, msg);
        }
    }
}
