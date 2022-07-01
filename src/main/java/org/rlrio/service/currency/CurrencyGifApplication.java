package org.rlrio.service.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
@PropertySource(value = "file:secrets.yml")
public class CurrencyGifApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyGifApplication.class, args);
    }
}
