package com.example.dbproject.mapper;

import com.example.dbproject.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select pid,pname,category,img,price from product order by launch_time desc limit #{start}, #{pagesize}")
    public List<Product> selectHeadPage(Integer start, Integer pagesize);

    @Select("select pid,pname,category,img,price from product where category = #{category} order by launch_time desc limit #{start}, #{pagesize}")
    public List<Product> selectCategoryByPage(String category, Integer start, Integer pagesize);

    @Select("select pid,pname,category,img,price from product where pname like #{pname} order by launch_time desc limit #{start}, #{pagesize}")
    public List<Product> selectLikeNameByPage(String pname, Integer start, Integer pagesize);

    @Select("select * from product where pid = #{pid}")
    public Product selectDetail(Integer pid);

// @Select("select * from product order by launch_time desc limit 0,5")
// public List<Product> selectSlideShow();

    @Select("select count(*) from product")
    public Integer countHeadPage();

    @Select("select count(*) from product where category = #{category}")
    public Integer countCategoryByPage(String category);

    @Select("select count(*) from product where pname like #{pname}")
    public Integer countLikeNameByPage(String pname);
    @Select("select * from product where pid=#{pid}")
    Product selectByPid(Integer pid);
}
