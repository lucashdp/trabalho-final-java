package com.javaee.stockmarket.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.javaee.stockmarket.api.v1.model.StockDTO;
import com.javaee.stockmarket.api.v1.model.StockViewDTO;
import com.javaee.stockmarket.api.v1.model.UserDTO;
import com.javaee.stockmarket.config.EmailConfig;
import com.javaee.stockmarket.config.RabbitMQConfig;
import com.javaee.stockmarket.domain.Shopping;
import com.javaee.stockmarket.services.StockService;
import com.javaee.stockmarket.services.UserService;

@Component
public class ShoppingListener {

    private StockService stockService;
    private UserService userService;

    public ShoppingListener(StockService stockService, UserService userService) {
        this.stockService = stockService;
        this.userService = userService;
    }

    static final Logger logger = LoggerFactory.getLogger(ShoppingListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGES)
    public void processPurchase(Shopping shopping) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(shopping.getStock_id());
        stockDTO.setOwner_id(shopping.getBuyer_id());
        stockDTO.setPrice(shopping.getPrice());

        String buyerEmail = userService.getById(shopping.getBuyer_id()).getEmail();
        
        String sellerEmail = "";
        UserDTO seller = stockService.getById(shopping.getStock_id()).getOwner();
        if (seller != null)
            sellerEmail = seller.getEmail();

        try {
            stockService.save(shopping.getStock_id(), stockDTO);
            logger.info("Purchase processed successfully!");
            sendMail(buyerEmail, "Compra efetuada com sucesso",
                    "Olá caro cliente, sua compra foi efetuada com sucesso, parabéns !");
            sendMail(sellerEmail, "Venda efetuada com sucesso",
                    "Olá caro cliente, sua venda foi efetuada com sucesso, parabéns !");
        } catch (Exception ex) {
            logger.info("Failed to process the purchase!");
            sendMail(buyerEmail, "Erro ao efetuar compra",
                    "Olá caro cliente, infelizmente houve um problema com sua compra, contate o suporte !");
            sendMail(sellerEmail, "Erro ao efetuar venda",
                    "Olá caro cliente, infelizmente houve um problema com sua venda, contate o suporte !");
        }
    }

    private void sendMail(String toEmail, String subject, String body) {
        final String fromEmail = "*****@outlook.com";
        final String password = "*******";

        EmailConfig config = new EmailConfig();

        config.sendEmail(fromEmail, password, toEmail, subject, body);
    }
}
