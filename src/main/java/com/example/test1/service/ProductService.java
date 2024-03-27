package com.example.test1.service;

import com.example.test1.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.test1.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void registerNewProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public Optional<Product> getProductById(long productId) {
        return productRepository.findById(productId);
    }

    public void updateProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public void removeProduct(long productId) {
        productRepository.deleteById(productId);
    }
}

