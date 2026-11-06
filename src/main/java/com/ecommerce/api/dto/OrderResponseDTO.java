package com.ecommerce.api.dto;

import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public record OrderResponseDTO(
        Long id,
        Long userId,
        Double totalValue,
        Integer totalItens,
        String status,
        String paymentMethod,
        LocalDateTime createdAt,
        List<OrderItemResponseDTO> itens
) {

    public static OrderResponseDTO fromOrder(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getUser().getId(),
                order.getTotalValue(),
                order.getTotalItens(),
                order.getStatus().getDescription(),
                order.getPaymentMethod().getDescription(),
                order.getCreatedAt(),
                order.getItens().stream()
                        .map(OrderItemResponseDTO::fromOrderItem).toList()
        );
    }

}

record OrderItemResponseDTO(
        Long productId,
        String productName,
        Integer quantity,
        Double unitPrice,
        Double subtotal
){
    public static OrderItemResponseDTO fromOrderItem(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getProduct().getId(),
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderItem.getSubtotal()
        );
    }
}
