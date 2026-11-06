package com.ecommerce.api;

import com.ecommerce.api.dto.ProductDTO;
import com.ecommerce.api.dto.ProductUpdatePriceRequestDTO;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/products")
public class ProductRestApi {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        List<Product> allProducts = (List<Product>) productService.findAllProducts(null);
        return StreamSupport.stream(allProducts.spliterator(), false)
                .map(ProductDTO::fromProduct)
                .toList(); //trtansformando os objetos product em productDTO
    }

    @PutMapping("/{id}/price")
    public ProductDTO updatePrice(@PathVariable Long id, @RequestBody ProductUpdatePriceRequestDTO request){
        Product product = productService.updatePrice(id, request.price());
        return ProductDTO.fromProduct(product);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
    }

}
