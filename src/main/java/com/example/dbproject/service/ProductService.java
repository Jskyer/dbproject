package com.example.dbproject.service;

import com.example.dbproject.entity.Product;
import com.example.dbproject.mapper.ProductMapper;
import com.example.dbproject.service.ex.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> showList(Integer start, Integer pagesize) throws Exception{
        List<Product> products = null;
        try{
            products = productMapper.selectHeadPage(start, pagesize);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ProductException("showList failure");
        }
        return products;
    }

    public List<Product> showListByCategory(String category, Integer start, Integer pagesize) throws Exception{
        List<Product> products = null;
        try{
            products = productMapper.selectCategoryByPage(category, start, pagesize);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ProductException("showListByCategory failure");
        }
        return products;
    }

    public List<Product> searchProducts(String pname, Integer start, Integer pagesize) throws Exception{
        pname = "%" + pname + "%";
        List<Product> products = null;
        try{
            products = productMapper.selectLikeNameByPage(pname, start, pagesize);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ProductException("searchProducts failure");
        }
        return products;
    }

    public Product showDetails(Integer pid) throws Exception{
        Product product = null;
        try{
            product = productMapper.selectDetail(pid);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new ProductException("showDetails failure");
        }
        return product;
    }

    public Integer countHeadPage(){
        Integer cnt = productMapper.countHeadPage();
        return cnt;
    }

    public Integer countCategoryByPage(String category){
        Integer cnt = productMapper.countCategoryByPage(category);
        return cnt;
    }

    public Integer countLikeNameByPage(String pname){
        pname = "%" + pname + "%";
        Integer cnt = productMapper.countLikeNameByPage(pname);
        return cnt;
    }


}
