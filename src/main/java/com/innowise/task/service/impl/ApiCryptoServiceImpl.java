package com.innowise.task.service.impl;

import com.google.gson.Gson;
import com.innowise.task.entity.dto.CryptoDTO;
import com.innowise.task.service.ApiCryptoService;
import org.springframework.stereotype.Service;

@Service
public class ApiCryptoServiceImpl implements ApiCryptoService {
    @Override
    public CryptoDTO[] getCryptoFromApi(String apiResponse) {
        Gson gson = new Gson();
        return gson.fromJson(apiResponse, CryptoDTO[].class);
    }
}
