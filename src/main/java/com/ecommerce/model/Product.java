package com.ecommerce.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    private String description;

    public Product() {
        this.status = ProductStatus.CREATED;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductStatus getStatus() {

        return status;
    }

    public void setStatus(ProductStatus status) {

        this.status = status;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", state='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public enum ProductStatus {
        CREATED("Criado"),
        DELETED("Deletado"),
        SUSPENDED("Suspenso");

        private final String description;

        ProductStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public Product delete(){
        this.status = ProductStatus.DELETED;
        return this;
    }
}
