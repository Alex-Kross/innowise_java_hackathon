package com.innowise.task.service;

import com.innowise.task.entity.dto.CryptoApi;

public interface ApiCryptoService {
    CryptoApi[] getCryptoFromApi(String apiResponse);
}
