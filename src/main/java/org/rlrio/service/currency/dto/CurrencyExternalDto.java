package org.rlrio.service.currency.dto;

import java.util.Map;
import lombok.Data;

@Data
public class CurrencyExternalDto {

    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    private Map<String, Double> rates;
}