package com.example.dbproject;

import com.example.dbproject.entity.Cart;
import com.example.dbproject.mapper.CartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CartTests {
    @Autowired
    CartMapper cartMapper;
    @Test
    void test1() {
        try{
            Cart cart =new Cart();
            cart.setId(1);
            cart.setPid(2);
            cart.setAmount(1);
            //Integer res = cartMapper.insertCart(1,2,1);
//            System.out.println(res);
//            System.out.println("test success");

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
