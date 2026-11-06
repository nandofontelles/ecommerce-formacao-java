package com.ecommerce.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UserTest {

    private User user;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private boolean newsletter;


    @BeforeEach
    void setup(){
        System.out.println("Setuping data");
        user = new User();

        firstName = "Unit";
        lastName = "Test";
        email = "unittest@email.com";
        phone = "81999999999";
        password = "test@123";
        newsletter = true;

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setNewsletter(newsletter);
    }

    @Test
    void testUserCreation(){
        System.out.println("Testing User Creation");

        Assertions.assertEquals(firstName, user.getFirstName());
        Assertions.assertEquals(lastName, user.getLastName());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(phone, user.getPhone());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertTrue(user.getNewsletter(), "Newsletter should be true");
        Assertions.assertEquals(User.UserStatus.ACTIVE, user.getStatus());
        Assertions.assertEquals(LocalDateTime.now().toLocalDate(),
                user.getCreatedAt().toLocalDate(),"Creation date should be close to today");
    }

    @Test
    void testUserUpdateStatus(){
        System.out.println("Testing user status changes...");

        User user = new User();
        Assertions.assertEquals(User.UserStatus.ACTIVE, user.getStatus());

        user.inactive();
        Assertions.assertEquals(User.UserStatus.INACTIVE, user.getStatus());

        user.suspended();
        Assertions.assertEquals(User.UserStatus.SUSPENDED, user.getStatus());

        user.blocked();
        Assertions.assertEquals(User.UserStatus.BLOCKED, user.getStatus());


    }

}
