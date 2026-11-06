package com.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double totalValue;
    private Integer totalItens;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> itens = new ArrayList<>();

    public Order() {

        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.paymentMethod = Order.PaymentMethod.PIX;
    }

    public Order(User user) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.paymentMethod = Order.PaymentMethod.PIX;
    }

    public Order(User user, Double totalValue, Integer totalItens) {
        this.user = user;
        this.totalValue = totalValue;
        this.totalItens = totalItens;

        this.created();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItem> getItens() {
        return itens;
    }

    public void setItens(List<OrderItem> itens) {
        this.itens = itens;
    }

    public void addItem(OrderItem item){
        itens.add(item);
        item.setOrder(this);
    }

    public Order created(){
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        return this;
    }

    public enum OrderStatus {
        PENDING("Pendente"),
        CONFIRMED("Confirmado"),
        PAID("Pago"),
        SHIPPED("Enviado"),
        DELIVERED("Entregue"),
        CANCELED("Cancelado");

        private final String description;

        OrderStatus(String description){
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum PaymentMethod {
        PIX ("PIX"),
        CREDIT_CARD("Cartao de Credito"),
        DEBIT_CARD("Cartao de Debito"),
        BOLETO("Boleto");

        private final String description;

        PaymentMethod(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", totalValue=" + totalValue +
                ", totalItens=" + totalItens +
                ", status=" + status +
                ", paymentMethod=" + paymentMethod +
                ", createdAt=" + createdAt +
                '}';
    }
}
