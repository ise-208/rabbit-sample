package com.ise.rabbitsample;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner  {

    private final RabbitTemplate rabbitTemplate;

    private final Receiver receiver;


    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

//    @Override
//    public void run(String... args) throws Exception {
//        rabbitTemplate.convertAndSend(RabbitSampleApplication.topicExchangeName, "foo.bar.baz", "Hello World MQ");
//        receiver.getLatch().await(1000, TimeUnit.MICROSECONDS);
//    }
}
