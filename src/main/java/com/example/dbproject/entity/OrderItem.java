package com.example.dbproject.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OrderItem {
    private int oid;
    private int id;
    private int pid;
    private int amount;
    private String dest;
    private Date orderdate;
    private int group_num;
}
