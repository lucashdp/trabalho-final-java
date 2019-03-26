package com.javaee.stockmarket.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.javaee.stockmarket.config.RabbitMQConfig;
import com.javaee.stockmarket.domain.Shopping;

@Component
public class ShoppingListener {

	static final Logger logger = LoggerFactory.getLogger(ShoppingListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGES)
    public void processMessage(Shopping shopping) {
        logger.info("Purchase Received");
    }
}
