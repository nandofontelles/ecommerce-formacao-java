package com.ecommerce.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product product;
    private Long id;
    private String name;
    private double price;
    private Category category;
    private Product.ProductStatus status;
    private String description;

    @BeforeEach
    void setup(){
        System.out.println("Setuping up data");
        product = new Product();

        id = 1L;
        name = "unit test product";
        price = 19.99;
        category = Category.AUTOMOTIVE;
        description = "teste unitario de produto";

        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);

    }

    @Test
    void testProductCreation(){
        System.out.println("Testing product creation");
        Assertions.assertEquals(id, product.getId());
        Assertions.assertEquals(name, product.getName());
        Assertions.assertEquals(price, product.getPrice());
        Assertions.assertEquals(category, product.getCategory());
        Assertions.assertEquals(description, product.getDescription());
        Assertions.assertEquals(Product.ProductStatus.CREATED, product.getStatus());
    }

    @Test
    void testProductDelete(){
        System.out.println("Testing delete product");
        product.delete();

        Assertions.assertEquals(Product.ProductStatus.DELETED, product.getStatus());
    }
}
