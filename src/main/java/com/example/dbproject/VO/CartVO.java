package com.example.dbproject.VO;

import lombok.Data;

import java.io.Serializable;
@Data
/**购物车数据Vo类(Value Object）*/
public class CartVO implements Serializable {
    private Integer cid;
    private Integer id;
    private Integer pid;
    private Integer price;
    private Integer amount;
    private String pname;
    private String img;
}
