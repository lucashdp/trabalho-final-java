package com.javaee.stockmarket.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.javaee.stockmarket.repositories.*;

@Component
@Profile({ "dev" })
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    public ApplicationBootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
    }

    private void loadCategories() {
    }

}
