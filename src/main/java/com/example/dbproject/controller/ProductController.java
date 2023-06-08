package com.example.dbproject.controller;

import com.example.dbproject.entity.Product;
import com.example.dbproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/showlist")
    @ResponseBody
    public Map<String, Object> showlist(@RequestParam("start") Integer start, @RequestParam("pageSize") Integer pageSize){
        List<Product> products = null;
        Integer total = null;
        try{
            products = productService.showList(start, pageSize);
            total = productService.countHeadPage();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", products);
        return map;
    }

    @GetMapping("/showListByCategory")
    @ResponseBody
    public Map<String, Object> showListByCategory(@RequestParam("category") String category,
                                            @RequestParam("start") Integer start,
                                            @RequestParam("pageSize") Integer pageSize){
        List<Product> products = null;
        Integer total = null;
        try{
            products = productService.showListByCategory(category, start, pageSize);
            total = productService.countCategoryByPage(category);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", products);
        return map;
    }

    @GetMapping("/searchProducts")
    @ResponseBody
    public Map<String, Object> searchProducts(@RequestParam("pname") String pname,
                                        @RequestParam("start") Integer start,
                                        @RequestParam("pageSize") Integer pageSize){
        List<Product> products = null;
        Integer total = null;
        try{
            products = productService.searchProducts(pname, start, pageSize);
            total = productService.countLikeNameByPage(pname);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", products);
        return map;
    }

    @GetMapping("/products/details/{pid}")
    @ResponseBody
    public Product showDetails(@PathVariable("pid") Integer pid){
        Product product = null;
        try{
            product = productService.showDetails(pid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return product;
    }

//    @GetMapping("/showSlide")
//    @ResponseBody
//    public List<Product> showSlide(){
//        List<Product> products = null;
//        try{
//            products = productService.showSlide();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        return products;
//    }


}
