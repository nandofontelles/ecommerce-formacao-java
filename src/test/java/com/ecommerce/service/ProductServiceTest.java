package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private final Long productId = 1L;
    private final double newPrice = 99.99;
    private final double startPrice = 3500;

    private Product product;

    @BeforeEach
    void setUp(){
        product = new Product();
        product.setId(productId);
        product.setName("teste product service");
        product.setPrice(startPrice);

    }

    @Test
    void testUpdatePrice(){

        Product productSaved = new Product();
        productSaved.setPrice(newPrice);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(productSaved);

        Optional<Product> productReturn = productService.findById(productId);
        Assertions.assertTrue(productReturn.isPresent());
        Assertions.assertNotNull(productReturn.get());
        Assertions.assertEquals(startPrice, productReturn.get().getPrice());

        Product productAfter = productService.updatePrice(productId, newPrice);

        Assertions.assertNotNull(productAfter);
        Assertions.assertEquals(newPrice, productAfter.getPrice());

        verify(productRepository, times(2)).findById(productId);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdatePrice_ProductNotFound(){
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.updatePrice(productId, newPrice);
        });

        Assertions.assertEquals("Product not found with id: " + productId, exception.getMessage());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).save(any(Product.class));

    }

    @Test
    void testProductSoftDelete(){
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product.delete())).thenReturn(product.delete());

        productService.deleteById(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository,times(1)).save(product.delete());
    }
}
