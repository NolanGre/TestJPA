package com.example.test1.product;

import com.example.test1.controller.ProductController;
import com.example.test1.model.Product;
import com.example.test1.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void addProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"Bob\",\n" +
                        "    \"description\": \"erwrw\",\n" +
                        "    \"price\": 564.21,\n" +
                        "    \"quantity\": 14\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void getProductById() throws Exception {
        // given
        long productId = 1L;
        Product product = new Product(productId, "Bob", "erwrw", 564.21, 14);
        when(productService.getProductById(productId))
                .thenReturn(Optional.of(product));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/product")
                .param("productId", String.valueOf(productId)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"name\": \"Bob\",\n" +
                        "    \"description\": \"erwrw\",\n" +
                        "    \"price\": 564.21,\n" +
                        "    \"quantity\": 14\n" +
                        "}"));
    }

    @Test
    void updateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/product/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"notBob\",\n" +
                        "    \"description\": \"fffff\",\n" +
                        "    \"price\": 13.12,\n" +
                        "    \"quantity\": 1\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProduct() throws Exception {
        // given
        long productId = 1L;

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/product")
                .param("productId", String.valueOf(productId)))
                .andExpect(status().isNoContent());
    }
}