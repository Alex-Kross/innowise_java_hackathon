package com.innowise.task.entity.mapper;

import com.innowise.task.entity.dto.CryptoApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoMapper {
    @Value("${crypto.number}")
    private int numberCrypto = 10;

    public String fromCryptoApiToString(CryptoApi[] cryptoApi) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < numberCrypto; i++) {
            stringBuffer.append("Crypto - " + cryptoApi[i].getName());
            stringBuffer.append(" , price = " + cryptoApi[i].getPrice() + "\n");
        }
        return stringBuffer.toString();
    }
}
