package com.example.test1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.test1.model.Product;
import com.example.test1.service.ProductService;

import java.util.Optional;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/register")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productService.registerNewProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product")
    public ResponseEntity<Product> getProductById(@RequestParam(name = "productId") long productId) {
        Optional<Product> product = productService.getProductById(productId);

        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/product/update")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        return (productService.updateProduct(product)) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/product")
    public ResponseEntity<Void> deleteProduct(@RequestParam(name = "productId") long productId) {
        return (productService.removeProduct(productId)) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
