package com.javaee.stockmarket.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.javaee.stockmarket.config.RabbitMQConfig;
import com.javaee.stockmarket.domain.Shopping;

@Service
public class ShoppingServiceImpl implements ShoppingService{

	private final RabbitTemplate rabbitTemplate;
	 
    public ShoppingServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    @Override
    public void requestPurchase(Shopping shopping) {
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_MESSAGES, shopping);
    }
}
