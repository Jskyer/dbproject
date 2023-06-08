package com.example.dbproject.mapper;

import com.example.dbproject.entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CustomerMapper {

    @Select("select * from customer where username = #{username}")
    public Customer selectByName(String username);

    @Select("select * from customer where id = #{id}")
    public Customer selectById(Integer id);

    @Insert("insert into customer(username,password,account,salt,next_group_num) values(#{username}, #{password}, #{account}, #{salt},0)")
    public void insertCustomer(String username, String password, Integer account, String salt);

    @Update("update customer set password = #{password} where id = #{id}")
    public void updatePassword(Integer id, String password);

    @Select("select account from customer where id = #{id}")
    public Integer selectAccount(Integer id);

    @Update("update customer set account = account + #{delt} where id = #{id}")
    public void updateAccount(Integer id, Integer delt);

    @Update("update customer set account = account - #{delt} where id = #{id}")
    public void deductAccount(Integer id, Integer delt);
    @Update("update customer set next_group_num = #{next_group_num} where id = #{id} ")
    public void updateNext(Integer id,Integer next_group_num);



}
