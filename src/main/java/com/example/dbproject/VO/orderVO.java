package com.example.dbproject.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class orderVO {
    private Integer oid;
    private Integer id;
    private Integer pid;
    private Integer price;
    private Integer amount;
    private String pname;
    private String img;
    private Integer group_num;
    private String dest;
    private String detail;

}
