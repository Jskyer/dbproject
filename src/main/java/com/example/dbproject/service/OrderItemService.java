package com.example.dbproject.service;

import com.example.dbproject.VO.GrpNum;
import com.example.dbproject.VO.OrderVO;
import com.example.dbproject.mapper.CartMapper;
import com.example.dbproject.mapper.OrderItemMapper;
import com.example.dbproject.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;

    //返回本次订单总金额
//    public Integer create(Integer id, Integer[] cid, String dest, Integer group_num, Date orderdate){
//        List<CartVO> list=cartService.selectVOByCid(id,cid);
//        //计算产品总价
//        /**总价在这里，如何去调用*/
//        Integer totalPrice=0;
//        for (CartVO c:list){
//            totalPrice+= c.getPrice()*c.getAmount();
//            //创建一个订单项数据对象
//            OrderItem orderItem=new OrderItem();
//            orderItem.setId(id);
//            orderItem.setDest(dest);
//            orderItem.setAmount(c.getAmount());
//            orderItem.setGroup_num(group_num);
//            orderItem.setPid(c.getPid());
//            orderItem.setOrderdate(orderdate);
//            Integer rows= orderItemMapper.insertOrderItem(orderItem);
//            if(rows!=1){
//                throw new InsertException("插入异常");
//            }
//            //totalPrice=list
//        }
//
//        return totalPrice;
//    }
//
//    public List<OrderItem> selectOrder(Integer id, Integer[] cid, Integer oid, Integer group_num,Integer totalPrice){
//        List<CartVO> list=cartService.selectVOByCid(id,cid);
//        OrderItem orderItem=new OrderItem();
//        orderItem.setId(id);
//        orderItem.setOid(oid);
//
////        //将总金额暂时放在amount中
////        orderItem.setAmount(totalPrice);
//        List<OrderItem> data=orderItemMapper.selectSingleGroup(id,group_num);
//
//        return data;
//    }
    public Integer findPrice(Integer id, Integer group_num){
        Integer totalPrice= orderItemMapper.getPrice(id,group_num);
        return totalPrice;
    }

    public List<OrderVO> showOrder(Integer id){
        List<OrderVO> result=orderItemMapper.selectVOById(id);
        if(result==null){//购物车中原来没有要添加到商品
                throw new InsertException("没有东西!");
        }else{
            System.out.println("有东西！");
        }
        return result;
    }
    public List<GrpNum> findgrp(Integer id){
        List<GrpNum> num= orderItemMapper.showGrp(id);
        System.out.println("num:"+num);
        return num;
    }
    public void signOrder(Integer id,Integer group_num){
        List<OrderVO> result=orderItemMapper.selectVOByGroupNum(group_num);
        if(result==null){
            System.out.println("没有订单");
        }
        Integer rows=orderItemMapper.deleteOrder(id,group_num);
        if(rows!=1){
            throw new InsertException("删除失败，发生异常!");
        }
    }

}
