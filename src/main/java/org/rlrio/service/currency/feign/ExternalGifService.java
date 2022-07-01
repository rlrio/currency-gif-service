package org.rlrio.service.currency.feign;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gifExternalServiceUrl", path = "/", url = "${gifExternalServiceUrl}")
public interface ExternalGifService {

    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    String searchGifsByKeyWord(@RequestParam String api_key, @RequestParam String q);
}
