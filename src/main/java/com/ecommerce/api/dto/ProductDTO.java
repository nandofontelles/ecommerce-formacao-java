package com.ecommerce.api.dto;


import com.ecommerce.model.Category;
import com.ecommerce.model.Product;

public record ProductDTO(
        Long id,
        String name,
        double price,
        Category category,
        String state,
        String description){
    public static ProductDTO fromProduct(Product product){
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getStatus().name(),
                product.getDescription()
        );
    }
}
