package com.example.dbproject;

import com.example.dbproject.entity.Cart;
import com.example.dbproject.mapper.CartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CartTests {
    @Autowired
    CartMapper cartMapper;
    @Test
    void test1() {
        try{
//            List<Cart> list = cartMapper.selectCart(1);
//
//            System.out.println(list);
//            cartMapper.insertCart(1,2);
//            cartMapper.insertCart(1,1);
//            cartMapper.deleteCart(5);
//            List a=cartMapper.searchCart(1);
//            System.out.println(a);
//            cartMapper.updateAmount(1,2,3);
            Cart cart =new Cart();
//            cart.setCid(17);
            cart.setId(1);
            cart.setPid(2);
            cart.setAmount(1);
            Integer res = cartMapper.insertCart(1,2,1);
            System.out.println(res);
            System.out.println("test success");

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
