package com.example.dbproject.controller;

import com.example.dbproject.VO.GrpNum;
import com.example.dbproject.VO.OrderVO;
import com.example.dbproject.service.CartService;
import com.example.dbproject.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CartService cartService;
//    @RequestMapping("selectOrder")
//    public List<OrderItem> selectOrder(Integer id, Integer[] cid, String dest, Integer group_num, Date orderdate){
//        OrderItem orderItem=new OrderItem();
//        List<OrderItem> data=orderItemService.selectOrder(id,cid,orderItem.getOid(),group_num, orderItemService.create(id,cid,dest,group_num,orderdate));
//        return data;
//    }

//    @RequestMapping("create")
//    public Integer create(Integer id, Integer[] cid, String dest, Integer group_num, Date orderdate){
//        Integer data=orderItemService.create(id,cid,dest,group_num,orderdate);
//        return data;
//    }
    @RequestMapping("findPrice")
    public Integer findPrice(Integer id, Integer group_num){
        Integer data=orderItemService.findPrice(id,group_num);
        return data;
    }

    @GetMapping("/showOrder")
    @ResponseBody
    public Map<String, Object> showOrder(@RequestParam("id") Integer id){
        List<OrderVO> data=null;
        List<GrpNum> total=null;
        try{
            data = orderItemService.showOrder(id);
            total = orderItemService.findgrp(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", data);
        System.out.println("map="+map);
        return map;
    }

    @PostMapping("/signOrder")
    @ResponseBody
    public Map<String, Object> signOrder(@RequestParam("id") Integer id,@RequestParam("groupNum") Integer group_num) {
        String msg = "success";
        System.out.println("group_NUM="+group_num);
        try{
            orderItemService.signOrder(id,group_num);
        }catch(Exception e){
            msg = e.getMessage();
        }
        System.out.println(msg);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return map;
    }
}
