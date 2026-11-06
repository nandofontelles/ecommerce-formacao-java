package com.ecommerce.model;

import jakarta.persistence.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderTest {

    private Order order;
    private Long id;
    private User user;
    private Double totalValue;
    private Integer totalItens;
    private Order.OrderStatus status;
    private Order.PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private List<OrderItem> itens;


    @BeforeEach
    void setup(){
        System.out.println("data creation - order test");
        id=1L;
        totalValue=1000.0;
        totalItens=10;

        order = new Order();

        order.setId(id);
        createUser();
        order.setUser(user);
        order.setTotalItens(10);
        order.setTotalValue(1000.0);

        OrderItem orderItem = createOrderItem();
        itens = new ArrayList<OrderItem>();
        itens.add(orderItem);
        order.addItem(orderItem);

    }

    OrderItem createOrderItem(){
        OrderItem orderItem = new OrderItem();

        id = 1L;
        double unitPrice = 100;
        int quantity = 10;
        double subtotal = unitPrice * quantity;

        orderItem.setId(1L);
        orderItem.setUnitPrice(unitPrice);
        orderItem.setQuantity(quantity);
        orderItem.setSubtotal(subtotal);
        orderItem.setOrder(order);
        orderItem.setProduct(createProduct());

        return orderItem;
    }

    Product createProduct(){
        System.out.println("creating product test");
        Product product = new Product();

        product.setId(1L);
        product.setName("product teste");
        product.setPrice(100);

        product.setDescription("product unit teste");

        return product;
    }


    @Test
    void testOrderCreation(){
        System.out.println("Testing Order Creation");
        Assertions.assertEquals(id, order.getId());
        Assertions.assertEquals(user, order.getUser());
        Assertions.assertEquals(totalItens, order.getTotalItens());
        Assertions.assertEquals(Order.OrderStatus.PENDING, order.getStatus());
        Assertions.assertEquals(Order.PaymentMethod.PIX, order.getPaymentMethod());
        Assertions.assertEquals(LocalDateTime.now().toLocalDate(), order.getCreatedAt().toLocalDate());
        Assertions.assertEquals(itens, order.getItens());

    }

    void createUser(){
        System.out.println("Creating user for unit test ORDER ITEM");
        user = new User();

        user.setFirstName("teste unit");
        user.setLastName("order item");
        user.setEmail("orderItem@email.com");
        user.setPhone("81999999999");
        user.setPassword("123");
        user.setNewsletter(true);

    }

}
