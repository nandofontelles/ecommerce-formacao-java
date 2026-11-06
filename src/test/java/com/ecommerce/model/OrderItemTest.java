package com.ecommerce.model;

import jakarta.persistence.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class OrderItemTest {

    private OrderItem orderItem;
    private Long id;
    private Order order;
    private Product product;
    private double unitPrice;
    private Integer quantity;
    private Double subtotal;

    @Test
    void testOrderItemCreation(){
        System.out.println("Testing Order Item Creation");

        orderItem = new OrderItem();

        id = 1L;
        unitPrice = 10.99;
        quantity = 10;
        subtotal = unitPrice * quantity;

        orderItem.setId(id);
        orderItem.setUnitPrice(unitPrice);
        orderItem.setQuantity(quantity);
        orderItem.setSubtotal(subtotal);
        createOrder();
        orderItem.setOrder(order);
        createProduct();
        orderItem.setProduct(product);


        Assertions.assertEquals(id, orderItem.getId());
        Assertions.assertEquals(order, orderItem.getOrder());
        Assertions.assertEquals(product, orderItem.getProduct());
        Assertions.assertEquals(unitPrice, orderItem.getUnitPrice());
        Assertions.assertEquals(quantity, orderItem.getQuantity());
        Assertions.assertEquals(subtotal, orderItem.getSubtotal());


    }

    void createProduct(){
        System.out.println("creating product test");
        product = new Product();

        product.setId(1L);
        product.setName("product teste");
        product.setPrice(10.99);

        product.setDescription("product unit teste");
    }

    void createOrder(){
        order = new Order();
        order.setId(1L);
        order.setCreatedAt(LocalDateTime.now());
        order.setUser(createUser());
        order.setTotalValue(unitPrice * quantity);
        order.setTotalItens(10);
    }

    User createUser(){
        System.out.println("Creating user for unit test ORDER ITEM");
        User user = new User();

        user.setFirstName("teste unit");
        user.setLastName("order item");
        user.setEmail("orderItem@email.com");
        user.setPhone("81999999999");
        user.setPassword("123");
        user.setNewsletter(true);

        return user;

    }
}
