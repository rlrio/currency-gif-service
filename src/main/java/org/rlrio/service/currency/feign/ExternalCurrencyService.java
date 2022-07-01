package org.rlrio.service.currency.feign;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "currencyExternalServiceUrl", path = "/api", url = "${currencyExternalServiceUrl}")
public interface ExternalCurrencyService {

    @GetMapping(value = "/latest.json", produces = APPLICATION_JSON_VALUE)
    String getLatestRates(@RequestParam String app_id,
                                       @RequestParam String base);

    @GetMapping(value = "/historical/{date}.json", produces = APPLICATION_JSON_VALUE)
    String getHistorical(@PathVariable String date, @RequestParam String app_id,
                                      @RequestParam String base);
}