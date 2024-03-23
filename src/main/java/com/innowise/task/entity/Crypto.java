package com.innowise.task.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Crypto {
    private Long ID;
    private double price;
    private String name;
    private LocalDateTime localDateTime;
}
