package com.javaee.stockmarket.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.javaee.stockmarket.api.v1.model.StockDTO;
import com.javaee.stockmarket.config.RabbitMQConfig;
import com.javaee.stockmarket.domain.Shopping;
import com.javaee.stockmarket.services.StockService;

@Component
public class ShoppingListener {

    private StockService stockService;

    public ShoppingListener(StockService stockService) {
        this.stockService = stockService;
    }

	static final Logger logger = LoggerFactory.getLogger(ShoppingListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGES)
    public void processPurchase(Shopping shopping) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(shopping.getStock_id());
        stockDTO.setOwner_id(shopping.getBuyer_id());
        stockDTO.setPrice(shopping.getPrice());
        try {
            stockService.save(shopping.getStock_id(), stockDTO);
            logger.info("Purchase processed successfully!");
        } catch(Exception ex){            
            logger.info("Failed to process the purchase!");
        }
    }
}
