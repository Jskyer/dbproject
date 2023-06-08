package com.example.dbproject.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Cart {
    private int cid;
    private int id;
    private int pid;
    private Date join_time;
    private int amount;
}
