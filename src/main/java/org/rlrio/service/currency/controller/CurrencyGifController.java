package org.rlrio.service.currency.controller;

import org.rlrio.service.currency.service.CurrencyGifService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CurrencyGifController {

    private final CurrencyGifService currencyGifService;

    public CurrencyGifController(CurrencyGifService currencyGifService) {
        this.currencyGifService = currencyGifService;
    }

    @GetMapping(produces = "image/webp")
    public ResponseEntity<byte[]> getGifOnRate(@RequestParam String desiredCurrency) {
        byte[] gifOnRate = currencyGifService.getGifOnRate(desiredCurrency);
        return ResponseEntity.ok(gifOnRate);
    }
}


