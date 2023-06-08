package com.example.dbproject.mapper;

import com.example.dbproject.VO.CartVO;
import com.example.dbproject.entity.Customer;
import com.example.dbproject.entity.OrderItem;
import com.example.dbproject.entity.Cart;
import com.example.dbproject.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    //查看购物车
    @Select("select * from cart natural join product where id = #{id} order by join_time desc")
    public List<Cart> selectCartAll(Integer id);
    //检查购物车
// @Select("select pid from cart where id=#{id} and cid=#{cid}")
// public List<Integer> selectPidCartByIdAndCid(Integer id,Integer[] cid);
// //选择查看某id用户购物车信息
    @Select("select cid,id,pid,price,amount,pname,img from cart as c natural join product as p where id=#{id}")
    public List<CartVO> selectCartVOById(Integer id);
    //修改amount增加delt那么多
    @Select("select * from cart where cid=#{cid}")
    Cart selectByCid(Integer cid);
    @Select("select cid,id,pid,price,amount,pname from cart as c natural join product as p where cid=#{cid}")
    List<CartVO> selectVOByCid(Integer[] cid);
    @Select("select cid,id,pid,price,amount,pname,img from cart natural join product where id=#{id}")
    List<CartVO> selectVOById(Integer id);
    @Update("update cart set amount = amount+ #{delt} where id = #{id} and pid = #{pid}")
    public Integer updateAmount(Integer id,Integer pid,Integer delt);
    //加入购物车
    @Insert("insert into cart(id, pid, amount) values(#{id}, #{pid}, #{amount})")
    public Integer insertCart(Integer id, Integer pid, Integer amount);
    @Select("select count(*) from cart where id=#{id}")
    public Integer selectCartCount(Integer id);
    //删除购物车中内容
    @Delete("delete from cart where cid = #{cid}")
    public Integer deleteCart(Integer cid);
    @Select("select * from cart where id=#{id} and pid=#{pid}")
    Cart selectCartByIdAndPid(Integer id, Integer pid);
    @Delete("delete from cart where id = #{id}")
    public Integer deleteAllCart(Integer id);
    @Select("select sum(amount*price) from orderitem natural join product where id = #{id} and group_num = #{group_num}")
    public Integer getPrice(Integer id,Integer group_num);
}
