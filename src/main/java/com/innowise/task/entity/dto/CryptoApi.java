package com.innowise.task.entity.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CryptoApi {
    @SerializedName("symbol")
    private String name;
    @SerializedName("price")
    private double price;
}
