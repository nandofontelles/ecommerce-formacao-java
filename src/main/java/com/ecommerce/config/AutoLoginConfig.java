package com.ecommerce.config;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Optional;

@Component
public class AutoLoginConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event){

        if (Arrays.asList(environment.getActiveProfiles()).contains("admin")){
            System.out.println("=".repeat(60));
            System.out.println("AUTO LOGIN ADMIN MODE ENABLE");
            System.out.println("=".repeat(60));

            Optional<User> adminUser = userRepository.findByEmail("admin@admin.com");

            if(adminUser.isPresent()){
                System.out.println("Admin user found: " + adminUser.get().getEmail());
                System.out.println("Admin will be automatically looged in on first request");
                System.out.println("Navigate to: http://localhost:8080/admin");
                System.out.println("=".repeat(60));

                if (applicationContext instanceof WebApplicationContext){
                    ((WebApplicationContext) applicationContext).getServletContext()
                            .setAttribute("autoLoginUser", adminUser.get());
                }
            } else {
                System.out.println("WARNING: Admin user not found");
                System.out.println("=".repeat(60));
            }
        }

    }
}
