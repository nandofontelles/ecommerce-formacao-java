package com.ecommerce.config;

import com.ecommerce.model.*;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Data Initialyzer run method executed");

        if (productRepository.count() > 0){
            System.out.println("Dados ja existem no banoco, pulando inicializacao...");
            return;
        }

        productRepository.saveAll(Arrays.asList(
            createProduct("teste 1", Category.AUTOMOTIVE, (long) 129.30, Product.ProductStatus.CREATED, "teste1"),
            createProduct("teste 2", Category.BEAUTY, (long) 99.99, Product.ProductStatus.CREATED, "teste2"),
            createProduct("teste 3", Category.ELECTRONICS, (long) 100.11, Product.ProductStatus.CREATED, "teste3"),
                createProduct("teste 4", Category.HOME_GARDEN, (long) 29.965, Product.ProductStatus.CREATED, "teste4"),
                createProduct("teste 5", Category.ELECTRONICS, (long) 1999, Product.ProductStatus.CREATED, "teste5")
                ));

        User joao = createUsers("Joao","Silva", "joao@email.com",
                "819999999", "joao@123", true, User.UserStatus.ACTIVE);
        User antonio = createUsers("Antonio","Jose", "antonio@email.com",
                "819999999", "antonio@123", false, User.UserStatus.ACTIVE);
        User maria = createUsers("Maria","Silva", "maria@email.com",
                "819999999", "maria@123", true, User.UserStatus.SUSPENDED);
        User lais = createUsers("Lais","Silva", "lais@email.com",
                "819999999", "lais@123", true, User.UserStatus.INACTIVE);

        User admin = createUsers("admin","admin", "admin@admin.com",
                "819999999", "admin@123", true, User.UserStatus.ACTIVE);

        userRepository.saveAll(Arrays.asList(joao, antonio, maria, lais, admin));

        Iterable<Product> allProducts = productRepository.findAll();
        Product[] products = new Product[5];
        int index = 0;
        for (Product p : allProducts){
            products[index++] = p;
        }

        orderRepository.saveAll(Arrays.asList(
           createOrder(joao, 99.90, 2, Order.OrderStatus.PENDING, Order.PaymentMethod.PIX,
                   LocalDateTime.now().minusDays(30), products[0], products[1]),
            createOrder(joao, 199.90, 2, Order.OrderStatus.DELIVERED, Order.PaymentMethod.PIX,
                    LocalDateTime.now().minusDays(30), products[1], products[2]),

                createOrder(antonio, 99.90, 2, Order.OrderStatus.CANCELED, Order.PaymentMethod.PIX,
                        LocalDateTime.now().minusDays(30), products[0], products[1]),
                createOrder(antonio, 199.90, 2, Order.OrderStatus.CONFIRMED, Order.PaymentMethod.PIX,
                        LocalDateTime.now().minusDays(30), products[1], products[2]),

                createOrder(maria, 99.90, 2, Order.OrderStatus.PENDING, Order.PaymentMethod.PIX,
                        LocalDateTime.now().minusDays(30), products[0], products[1]),
                createOrder(maria, 199.90, 2, Order.OrderStatus.CONFIRMED, Order.PaymentMethod.PIX,
                        LocalDateTime.now().minusDays(30), products[1], products[2]),

                createOrder(lais, 99.90, 2, Order.OrderStatus.CONFIRMED, Order.PaymentMethod.PIX,
                        LocalDateTime.now().minusDays(30), products[0], products[1]),
                createOrder(lais, 199.90, 2, Order.OrderStatus.SHIPPED, Order.PaymentMethod.PIX,
                        LocalDateTime.now().minusDays(30), products[1], products[2])

        ));

    }

    private static Product createProduct(String name, Category category, Long price, Product.ProductStatus status, String description) {
        Product product = new Product();

        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setStatus(status);
        product.setDescription(description);

        return product;
    }

    private static User createUsers(String firstName, String lastName, String email, String phone, String password, Boolean newsletter, User.UserStatus status) {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setNewsletter(newsletter);
        user.setStatus(status);

        return user;
    }

    private static Order createOrder(User user, Double totalValue, Integer totalItens,
                               Order.OrderStatus status, Order.PaymentMethod paymentMethod, LocalDateTime dateTime, Product product1, Product product2){

        Order order = new Order();

        order.setUser(user);
        order.setTotalValue(totalValue);
        order.setTotalItens(totalItens);
        order.setStatus(status);
        order.setPaymentMethod(paymentMethod);
        order.setCreatedAt(dateTime);


        List<OrderItem> orderItens = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem(product1, product1.getPrice(), 1, totalValue);
        orderItem1.setOrder(order);
        orderItens.add(orderItem1);


        OrderItem orderItem2 = new OrderItem(product2, product2.getPrice(), 1, totalValue);
        orderItem2.setOrder(order);
        orderItens.add(orderItem2);

        order.setItens(orderItens);

        return order;
    }
}
