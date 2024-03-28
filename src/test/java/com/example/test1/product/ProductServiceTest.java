package com.example.test1.product;

import com.example.test1.model.Product;
import com.example.test1.repo.ProductRepository;
import com.example.test1.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService underTest;

    @Mock
    private ProductRepository productRepository;

    @Test
    void registerNewProduct() {
        // given
        Product product = new Product(1L, "biba", "bumbumbah", 356.12, 99);

        // when
        underTest.registerNewProduct(product);

        // then
        verify(productRepository).saveAndFlush(product);
    }

    @Test
    void getProductById() {
        // given
        long id = 1L;

        // when
        underTest.getProductById(id);

        // then
        verify(productRepository).findById(id);
    }

    @Test
    void updateProduct() {
        // given
        Product product = new Product(1L, "biba", "bumbumbah", 356.12, 99);

        // when
        underTest.updateProduct(product);

        // then
        verify(productRepository).saveAndFlush(product);

    }

    @Test
    void removeProduct() {
        // given
        long id = 1L;

        // when
        underTest.removeProduct(id);

        // then
        verify(productRepository).deleteById(id);

    }
}