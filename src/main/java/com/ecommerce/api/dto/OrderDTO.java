package com.ecommerce.api.dto;

import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record OrderDTO(
        Long id,
        User user,
        Double totalValue,
        Integer totalItens,
        Order.OrderStatus status,
        Order.PaymentMethod paymentMethod,
        LocalDateTime createdAt,
        List<OrderItem> itens
) {
    public static OrderDTO fromOrder(Order order){
        return new OrderDTO(
                order.getId(),
                order.getUser(),
                order.getTotalValue(),
                order.getTotalItens(),
                order.getStatus(),
                order.getPaymentMethod(),
                order.getCreatedAt(),
                order.getItens()
        );
    }
}
