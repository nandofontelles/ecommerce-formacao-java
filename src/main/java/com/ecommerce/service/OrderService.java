package com.ecommerce.service;

import com.ecommerce.api.dto.OrderCreateRequestDTO;
import com.ecommerce.api.dto.OrderItemRequestDTO;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Order> findAllByStatus(Order.OrderStatus status, Boolean isAdmin, User currentUser) {
        Iterable<Order> orders;

        if (isAdmin){
            if (status != null){
                orders = orderRepository.findAllByStatus(status);
            } else {
                orders = orderRepository.findAll();
            }
        } else {
            if (status != null){
                orders = orderRepository.findAllByStatusAndUserId(status, currentUser.getId());
            } else {
                orders = orderRepository.findAllByUserId(currentUser.getId());
            }
        }

        return orders;

    }

    public Order createOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    public Iterable<Order> getOrders(){
        return orderRepository.findAll();
    }

    public double totalSales() {

        List<Order> allOrders = (List<Order>) orderRepository.findAll();
        return allOrders.stream()
                .mapToDouble(Order::getTotalValue).sum();

    }

    public Order createOrderFromApi(OrderCreateRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.userId()).orElseThrow(()
                -> new RuntimeException("User not found with id: "+requestDTO.userId()));

        Order order = new Order(user);

        double totalValue = 0.0;
        int totalItens = 0;

        for (OrderItemRequestDTO itemRequest : requestDTO.itens()){
            Product product = productRepository.findById(itemRequest.productId()).orElseThrow(()
                    -> new RuntimeException("Product not found with product id: " + itemRequest.productId()));

            double unitPrice = product.getPrice();
            double subtotal = unitPrice * itemRequest.quantity();

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setUnitPrice(unitPrice);
            orderItem.setSubtotal(subtotal);

            order.addItem(orderItem);

            totalItens += subtotal;
            totalItens += itemRequest.quantity();
        }

        order.setTotalValue(totalValue);
        order.setTotalItens(totalItens);

        return orderRepository.save(order);
    }
}
