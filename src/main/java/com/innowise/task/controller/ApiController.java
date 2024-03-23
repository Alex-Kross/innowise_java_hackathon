package com.innowise.task.controller;

import com.innowise.task.entity.dto.CryptoDTO;
import com.innowise.task.service.ApiCryptoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class ApiController {
    private final ApiCryptoService apiCryptoService;

    @Value("${crypto.uri}")
    private String uri;

    public ApiController(ApiCryptoService apiCryptoService) {
        this.apiCryptoService = apiCryptoService;
    }


//    public ApiController(ApiCryptoService apiCryptoService) {
//        this.apiCryptoService = apiCryptoService;
//    }


    @Scheduled(fixedDelayString = "${interval}")
    @Async
    public void getCrypto() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        String response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("disconnect to api");
        }
        CryptoDTO[] cryptoDTO = apiCryptoService.getCryptoFromApi(response);

//        weatherService.saveWeather(weatherFromApi);
    }
}
