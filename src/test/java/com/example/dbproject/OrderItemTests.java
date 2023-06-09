package com.example.dbproject;

import com.example.dbproject.mapper.OrderItemMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrderItemTests {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Test
    void test1() {
        try{
            Integer price = orderItemMapper.getPrice(1, 1);
            System.out.println(price);

            System.out.println("test success");
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
