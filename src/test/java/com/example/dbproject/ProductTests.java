package com.example.dbproject;

import com.example.dbproject.entity.Product;
import com.example.dbproject.mapper.ProductMapper;
import com.example.dbproject.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductTests {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductService productService;

    @Test
    void test1() {
        try{
            List<Product> products = productService.showList(990, 12);
            for(Product p : products){
                System.out.println(p);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
