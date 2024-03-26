package com.example.test1.service;

import org.springframework.stereotype.Service;
import com.example.test1.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> productList;
    private static long ID = 1;

    public ProductService() {
        productList = new ArrayList<>();
    }

    public void registerNewProduct(Product product) {
        product.setId(ID++);
        productList.add(product);
    }

    public Optional<Product> getProductById(long productId) {
        return productList.stream()
                .filter(element -> element.getId() == productId)
                .findFirst();
    }

    public boolean updateProduct(Product product) {
        if (productList.removeIf(p -> p.getId() == product.getId())) {
            productList.add(product);
            return true;
        }
        return false;
    }

    public boolean removeProduct(long productId) {
        return productList.removeIf(product -> product.getId() == productId);
    }
}

