package com.innowise.task.controller;

import com.innowise.task.entity.dto.CryptoApi;
import com.innowise.task.entity.mapper.CryptoMapper;
import com.innowise.task.service.ApiCryptoService;
import com.innowise.task.service.CryptocurrencyBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class ApiController {
    private final ApiCryptoService apiCryptoService;
    private final CryptocurrencyBot bot;

    @Value("${crypto.uri}")
    private String uri;

    public ApiController(ApiCryptoService apiCryptoService, CryptocurrencyBot bot) {
        this.apiCryptoService = apiCryptoService;
        this.bot = bot;
    }

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
        CryptoApi[] cryptoApi = apiCryptoService.getCryptoFromApi(response);
        Message message = new Message();
        message.setText("/current");
        Update update = new Update();
        update.setMessage(message);

        bot.setCryptoRate(new CryptoMapper().fromCryptoApiToString(cryptoApi));
        bot.onUpdateReceived(update);



//        weatherService.saveWeather(weatherFromApi);
    }
}
