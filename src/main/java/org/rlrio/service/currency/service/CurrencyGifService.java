package org.rlrio.service.currency.service;

import static org.rlrio.service.currency.exception.ExceptionMessage.INVALID_CURRENCY;
import static org.rlrio.service.currency.exception.ExceptionMessage.IO_EXCEPTION_MESSAGE;
import static org.rlrio.service.currency.exception.ExceptionMessage.JSON_EXCEPTION_MESSAGE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.rlrio.service.currency.dto.CurrencyExternalDto;
import org.rlrio.service.currency.dto.GifExternalDto;
import org.rlrio.service.currency.exception.CurrencyGifException;
import org.rlrio.service.currency.feign.ExternalCurrencyService;
import org.rlrio.service.currency.feign.ExternalGifService;
import org.rlrio.service.currency.util.CurrencyValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurrencyGifService {

    private static final String WEBP_URL_FORMAT = "%s/%s/giphy.webp";
    private final ExternalCurrencyService externalCurrencyService;
    private final ExternalGifService externalGifService;
    private final CurrencyValidator currencyValidator;
    private final String appId;
    private final String apiKey;
    private final String baseCurrency;
    private final String gifMediaUrl;
    private final String gifSearchKeyWordPositive = "rich";
    private final String gifSearchKeyWordNegative = "broke";
    private final ObjectMapper mapper;

    public CurrencyGifService(ExternalCurrencyService externalCurrencyService,
                              ExternalGifService externalGifService,
                              CurrencyValidator currencyValidator,
                              @Value("${appId}") String appId,
                              @Value("${gifApiKey}") String apiKey,
                              @Value("${baseCurrency}") String baseCurrency,
                              @Value("${gifMediaUrl}") String gifMediaUrl,
                              ObjectMapper mapper) {
        this.externalCurrencyService = externalCurrencyService;
        this.externalGifService = externalGifService;
        this.currencyValidator = currencyValidator;
        this.appId = appId;
        this.apiKey = apiKey;
        this.baseCurrency = baseCurrency;
        this.gifMediaUrl = gifMediaUrl;
        this.mapper = mapper;
    }

    public byte[] getGifOnRate(String desiredCurrency) {
        if (!currencyValidator.isValid(desiredCurrency)) {
            throw new IllegalArgumentException(INVALID_CURRENCY);
        }

        try {
            String gifPath = getGifSearchKeyWord(desiredCurrency.toUpperCase(Locale.ROOT));
            GifExternalDto gifExternalDto = mapper.readValue(externalGifService.searchGifsByKeyWord(apiKey, gifPath), GifExternalDto.class);
            String gifUrl = searchUrlFromGifDto(gifExternalDto);
            return loadGif(gifUrl);
        } catch (JsonProcessingException e) {
            throw new CurrencyGifException(JSON_EXCEPTION_MESSAGE);
        }
    }

    private String searchUrlFromGifDto(GifExternalDto gifDto) {
        Map<String, Integer> pagination = gifDto.getPagination();
        int imagesCount = pagination.get("count");
        int randomImageIndex = ThreadLocalRandom.current().nextInt(0, imagesCount);
        List<Map<String, Object>> data = gifDto.getData();
        Map<String, Object> randomData = data.get(randomImageIndex);
        String id = (String) randomData.get("id");
        return String.format(WEBP_URL_FORMAT, gifMediaUrl, id);
    }

    private byte[] loadGif(String url) {
        try (InputStream in = new URL(url).openStream()) {
            return in.readAllBytes();
        } catch (IOException ex) {
            throw new CurrencyGifException(IO_EXCEPTION_MESSAGE);
        }
    }

    private String getGifSearchKeyWord(String desiredCurrency) throws JsonProcessingException {
        Double latestRate = mapper.readValue(externalCurrencyService
                                .getLatestRates(appId, baseCurrency), CurrencyExternalDto.class)
                .getRates()
                .get(desiredCurrency);

        Double yesterdayRate = mapper.readValue(externalCurrencyService
                        .getHistorical(getYesterdayDate(), appId, baseCurrency), CurrencyExternalDto.class)
                .getRates()
                .get(desiredCurrency);

        return Double.compare(latestRate, yesterdayRate) > 0 ?
                gifSearchKeyWordPositive : gifSearchKeyWordNegative;
    }

    private String getYesterdayDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return now.getHour() > 10 ? formatter.format(now.minusDays(1)) :
                formatter.format(now.minusDays(2));
    }
}