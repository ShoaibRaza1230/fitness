package com.fitness.activityserivce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("${spring.exchange.name}")
	private String exchange;
	
	@Value("${spring.routing.key}")
	private String routingKey;
	
	@Value("${spring.queue.name}")
	private String queue;
	
	//defining queue, true make it qurable which mean data maintain if rabbitmq close
	@Bean
	public Queue activityQueue()
	{
		return new Queue(queue,true);
	}
	
	@Bean
	public DirectExchange activityExchange()
	{
		return new DirectExchange(exchange);
	}
	
	@Bean
	public Binding activityBinding(Queue activityQueue, DirectExchange activityExchange)
	{
		return  BindingBuilder.bind(activityQueue).to(activityExchange).with(routingKey);
	}
	
	//for converting object to json before sending to rabbitmq
	@Bean
	public MessageConverter jsonMessageConverter()
	{
		return new Jackson2JsonMessageConverter();
	}
}
