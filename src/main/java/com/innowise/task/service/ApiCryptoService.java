package com.innowise.task.service;

import com.innowise.task.entity.dto.CryptoDTO;

public interface ApiCryptoService {
    CryptoDTO[] getCryptoFromApi(String apiResponse);
}
