package com.example.dbproject.mapper;

import com.example.dbproject.VO.GrpNum;
import com.example.dbproject.VO.OrderVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface OrderItemMapper {
    //增加订单
    @Insert("insert into orderitem(id, pid, amount, dest,group_num) values(#{id}, #{pid}, #{amount}, #{dest}, #{group_num})")
    public Integer insertOrder(Integer id, Integer pid, Integer amount, String dest, Integer group_num);

//    @Insert("insert into orderitem(id,pid,amount,dest,group_num) values(#{order.id},#{order.pid}, #{order.amount}, #{order.dest}, #{order.group_num})")
//    public Integer insertOrderItem(OrderItem order);
    //算订单总额
    @Select("select sum(amount*price) from orderitem natural join product where id = #{id} and group_num = #{group_num}")
    public Integer getPrice(Integer id,Integer group_num);

    // 查看订单列表
//    @Select("select * from orderitem where id = #{id} order by group_num, orderdate desc")
//    public List<OrderItem> selectOrderItems(Integer id);

    // 查看单次订单列表
//    @Select("select * from orderitem where id = #{id} and group_num = #{group_num} order by orderdate desc")
//    public List<OrderItem> selectSingleGroup(Integer id,Integer group_num);

    //签收订单（删除订单项）
    @Delete("delete from orderitem where id = #{id} and group_num = #{group_num}")
    public Integer deleteOrder(Integer id,Integer group_num);
    @Select("select oid,id,pid,price,amount,pname,img,group_num,dest,detail from orderitem natural join product where id=#{id} order by group_num desc")
    List<OrderVO> selectVOById(Integer id);

//    @Select("select count(group_num) from orderitem where id=#{id}")
//    Integer selectGrp(Integer id);

    @Select("select group_num,count(group_num) as count from orderitem where id=#{id} group by group_num order by group_num desc ")
    List<GrpNum> showGrp(Integer id);

    @Select("select oid,id,pid,price,amount,pname,img,group_num,dest,detail,orderdate from orderitem natural join product where group_num=#{group_num} order by orderdate desc")
    List<OrderVO> selectVOByGroupNum(Integer group_num);

}