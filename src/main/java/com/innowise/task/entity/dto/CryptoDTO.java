package com.innowise.task.entity.dto;

import com.google.gson.annotations.SerializedName;

public class CryptoDTO {
    @SerializedName("symbol")
    private String name;
    @SerializedName("price")
    private double price;
}
