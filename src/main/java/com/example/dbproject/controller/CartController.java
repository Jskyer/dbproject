package com.example.dbproject.controller;

import com.example.dbproject.VO.CartVO;
import com.example.dbproject.entity.Product;
import com.example.dbproject.service.CartService;
import com.example.dbproject.service.CustomerService;
import com.example.dbproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/add_to_cart")
    @ResponseBody
    public Map<String, Object> addToCart(@RequestParam("id") Integer id, @RequestParam("pid") Integer pid, @RequestParam("amount") Integer amount){
        String msg = "success";
        try{
            cartService.addToCart(id,pid,amount);
        }catch(Exception e){
            msg = e.getMessage();
        }
        System.out.println(msg);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return map;
    }


    @PostMapping("/removeCartItem")
    @ResponseBody
    public Map<String, Object> deleteCart(@RequestParam("cid") Integer cid) {
        String msg = "success";
        try{
            cartService.deleteCart(cid);
        }catch(Exception e){
            msg = e.getMessage();
        }
        System.out.println(msg);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return map;
    }

//    @RequestMapping("/")
//    public List<CartVO> selectVOById(Integer id){
//        List<CartVO> data= cartService.selectVOById(id);
//        return data;
//    }

    @RequestMapping("{id}/num/add")
    public Integer addAmount(Integer cid,Integer id,Integer pid,Integer delt){
        Integer data= cartService.addAmount(cid,id,pid,delt);
        return data;
    }

//    @RequestMapping("/getCarts")
//    public List<CartVO> selectVOByCid(Integer[] cid,Integer id){
//        List<CartVO> data=cartService.selectVOByCid(id,cid);
//        return data;
//    }

    @RequestMapping("/getCarts")
    @ResponseBody
    public Map<String, Object> selectVOById(@RequestParam("id") Integer id){
        List<CartVO> data=null;
        Integer total=null;
        try{
            data = cartService.selectVOById(id);
            total = cartService.cartCount(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", data);
        //System.out.println("cjzsb"+map);
        return map;
    }


    @PostMapping("/checkout")
    @ResponseBody
    public Map<String, Object> checkoutCart(@RequestParam("id") Integer id,@RequestParam("address") String address) {
        String msg = "success";
        try{
            cartService.checkoutCart(id,address);
        }catch(Exception e){
            msg = e.getMessage();
        }
        System.out.println(msg);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return map;
    }





}


