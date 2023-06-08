package com.example.dbproject.service;

import com.example.dbproject.entity.Customer;
import com.example.dbproject.mapper.CustomerMapper;
import com.example.dbproject.service.ex.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public void register(String username, String password, Integer account) throws Exception{
        Customer customer = customerMapper.selectByName(username);
        if(customer != null){
            throw new CustomerException("已存在用户名");
        }else if(username.length() > 20){
            throw new CustomerException("用户名不能超过20个字符");
        }else {
            String salt = UUID.randomUUID().toString().toUpperCase();
            String md5Password = md5EncodePassword(password, salt);
//            System.out.println(md5Password);
//            System.out.println(md5Password.length());
//            System.out.println(salt.length());
            customerMapper.insertCustomer(username,md5Password,account,salt);
            System.out.println("register success");
        }
    }

    private String md5EncodePassword(String password, String salt){
        for(int i = 0; i < 3; i++){
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    public Customer login(String username, String password) throws CustomerException{
        Customer customer = customerMapper.selectByName(username);
        if(customer == null){
            throw new CustomerException("用户名不存在");
        }else {
            String salt = customer.getSalt();
            String truePassword = customer.getPassword();
            String testPassword = password;
            testPassword = md5EncodePassword(testPassword, salt);
            if(truePassword.equals(testPassword)){
                return customer;
            }else {
                throw new CustomerException("用户名或密码错误");
            }
        }
    }

    public void updatePassword(int id, String password) throws Exception{
        Customer customer = customerMapper.selectById(id);
        if(customer == null){
            throw new CustomerException("用户不存在");
        }
        String salt = customer.getSalt();
        String newPassword = md5EncodePassword(password, salt);
        customerMapper.updatePassword(id, newPassword);
    }

    public Integer showAccount(int id){
        return customerMapper.selectAccount(id);
    }

    public Customer showPersonalInfo(int id){
        return customerMapper.selectById(id);
    }

    public void updateAccount(int id, int delt) throws Exception{
        Integer balance = customerMapper.selectAccount(id);
        if(balance == null){
            throw new CustomerException("用户不存在");
        }

        if(delt < 0){
            if(Math.abs(delt) > balance || balance <= 0){
                throw new CustomerException("余额不足");
            }
        }

        customerMapper.updateAccount(id, delt);
    }





}
