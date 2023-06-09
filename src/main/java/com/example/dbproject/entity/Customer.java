package com.example.dbproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Customer {
    private int id;
    private String username;
    @JsonIgnore
    private String password;
    private Integer account;
    @JsonIgnore
    private String salt;
    private Integer next_group_num;
}
