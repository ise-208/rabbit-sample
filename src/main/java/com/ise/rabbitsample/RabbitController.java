package com.ise.rabbitsample;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@RestController
public class RabbitController {

    private final RabbitTemplate rabbitTemplate;

    private final Receiver receiver;

    public RabbitController(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() throws InterruptedException {
        rabbitTemplate.convertAndSend(RabbitSampleApplication.topicExchangeName, "foo.bar.baz", "controller");
        receiver.getLatch().await(1000, TimeUnit.MICROSECONDS);
        return "hello";
    }

}
