package com.example.dbproject.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Product implements Serializable {
    private int pid;
    private String pname;
    private String category;
    private String img;
    private String detail;
    private Date launch_time;
    private int price;

}
