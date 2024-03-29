package com.example.dbproject;

import com.example.dbproject.entity.Customer;
import com.example.dbproject.mapper.CustomerMapper;
import com.example.dbproject.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CustomerTests {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CustomerService customerService;

    @Test
    void test1() {
        try{
            Customer cust = customerMapper.selectByName("admin");
            System.out.println(cust);
            customerMapper.insertCustomer("Tim", "123", 255,"dyxsb");
            customerMapper.updatePassword(5, "1111");
            int money = customerMapper.selectAccount(5);
            System.out.println(money);
//            customerMapper.updateAccount(5, -1000);

//            customerService.register("apple", "1111", 1000);
//            String str = customerService.login("apple", "123");
//            System.out.println(str);

//            customerService.updatePassword(7, "123");
//            Integer acc = customerService.showAccount(5);
//            System.out.println(acc);
//            customerService.updateAccount(6, 1000);
            System.out.println("test success");
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
