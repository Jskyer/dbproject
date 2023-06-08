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
//            List<Product> products = productMapper.selectLatestByPage("图书类",0, 5);
//            for(Product product : products){
//                System.out.println(product);
//            }

//            List<Product> products = productMapper.selectLikeNameByPage("%%",0, 5);
//            for(Product product : products){
//                System.out.println(product);
//            }
//            Product product = productMapper.selectDetail(3);
//            System.out.println(product);
//            List<Product> products = productMapper.selectSlideShow();
//            System.out.println(products);

            List<Product> products = productService.showList(990, 12);
//            List<Product> products = productService.showListByCategory("女装", 0, 10);
//            List<Product> products = productService.searchProducts("ZARA", 0, 10);
//            List<Product> products = productService.showSlide();
            for(Product p : products){
                System.out.println(p);
            }

//            Product product = productService.showDetails(5);
//            System.out.println(product.getDetail());
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
