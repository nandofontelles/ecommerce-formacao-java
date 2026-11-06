package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session){

        if (session.getAttribute("currentUser") == null) {
            System.out.println("User not logged in. Redirecting to signin");
            return "redirect:/signin";
        }else{
            System.out.println("User ir logged in as: " + session.getAttribute("currentUser"));
            model.addAttribute("currentUser", session.getAttribute("currentUser"));
        }

        long totalProducts = productRepository.count();
        long totalUsers = userRepository.count();
        long totalOrders = orderRepository.count();

        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalOrders", totalOrders);

        Iterable<Order> allOrders = orderRepository.findAll();

        double totalSales = calculateTotalSales(allOrders);
        model.addAttribute("totalSales", totalSales);

        Iterable<Order> recentOrders = getRecentOrders(allOrders, 5);
        model.addAttribute("recentOrders", recentOrders);

        List<Map.Entry<Product, Integer>> topProducts = getTopProducts(allOrders, 5);
        model.addAttribute("topProducts", topProducts);

        model.addAttribute("adminSection", "dashboard");
        return "admin";
    }

    private List<Map.Entry<Product,Integer>> getTopProducts(Iterable<Order> allOrders, int limit) {
        Map<Product, Integer> productSaleCount = StreamSupport.stream(allOrders.spliterator(), false)
                .flatMap(order -> order.getItens().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::getProduct,
                        Collectors.summingInt(OrderItem::getQuantity)));



        return productSaleCount.entrySet().stream()
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    private Iterable<Order> getRecentOrders(Iterable<Order> allOrders, int limit) {
        List<Order> orderList = StreamSupport.stream(allOrders.spliterator(), false)
                .collect(Collectors.toList());

        orderList = orderList.stream().sorted(Comparator.comparing(Order::getCreatedAt).reversed())
                .collect(Collectors.toList());

        return orderList.stream().limit(limit).collect(Collectors.toList());
    }

    private double calculateTotalSales(Iterable<Order> allOrders) {

        return StreamSupport.stream(allOrders.spliterator(), false)
            .mapToDouble(Order::getTotalValue).sum();

    }
}
