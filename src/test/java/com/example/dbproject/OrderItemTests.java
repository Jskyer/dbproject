package com.example.dbproject;

import com.example.dbproject.entity.Customer;
import com.example.dbproject.entity.OrderItem;
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
//            List<OrderItem> list=orderItemMapper.selectOrderItems(1);
//            System.out.println(list);
//            orderItemMapper.insertOrder(2,2,10,"邓yx家里");
//            orderItemMapper.deleteOrder(7);
//            Integer a=orderItemMapper.getPrice(1);
//            System.out.println(a);
//            orderItemMapper.insertOrder(1,2,6,"这里",5);
//            List<OrderItem> list=orderItemMapper.selectOrderItems(1);
//            System.out.println(list);
//            List<Integer> sum=orderItemMapper.getPrice(1,1);
//            Integer sum2=orderItemMapper.getSum(sum);
//            System.out.println(sum);
//            System.out.println(sum2);
////            orderItemMapper.deleteOrder(1,5);
//            orderItemMapper.insertOrder(1,1,2,"where",4);
//            orderItemMapper.insertOrder(1,2,2,"where",4);
//            orderItemMapper.insertOrder(1,3,2,"where",4);
//            orderItemMapper.insertOrder(2,1,2,"where",4);
//            orderItemMapper.insertOrder(2,2,2,"where",4);
//            orderItemMapper.insertOrder(2,3,2,"where",4);
//            List<OrderItem> list=orderItemMapper.selectOrderItems(1);
//            System.out.println(list);
//            List<OrderItem> list2=orderItemMapper.selectOrderItems2(1,4);
//            System.out.println(list2);
            Integer price = orderItemMapper.getPrice(1, 1);
            System.out.println(price);

            System.out.println("test success");
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
