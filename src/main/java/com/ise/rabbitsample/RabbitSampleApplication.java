package com.ise.rabbitsample;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
public class RabbitSampleApplication {

	static final String topicExchangeName = "spring-boot-exchange";

	public static final String queueName = "spring-boot";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory factory, MessageListenerAdapter adapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(factory);
		container.setQueueNames(queueName);
		container.setMessageListener(adapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter() {
		return new MessageListenerAdapter();
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitSampleApplication.class, args);
	}

}
