package org.rlrio.service.currency.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rlrio.service.currency.dto.CurrencyExternalDto;
import org.rlrio.service.currency.dto.GifExternalDto;
import org.rlrio.service.currency.feign.ExternalCurrencyService;
import org.rlrio.service.currency.feign.ExternalGifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CurrencyGifServiceTest {

    @Autowired
    private CurrencyGifService service;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ExternalCurrencyService externalCurrencyService;

    @MockBean
    private ExternalGifService externalGifService;

    private String latestRate;
    private String historicalRate;
    private String gifData;

    @BeforeEach
    void prepareData() throws JsonProcessingException {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> data1 = new HashMap<>();
        data1.put("type", "gif");
        data1.put("id", "LdOyjZ7io5Msw");
        data1.put("url", "https://giphy.com/gifs/make-it-rain-get-paid-LdOyjZ7io5Msw");
        data1.put("slug", "make-it-rain-get-paid-LdOyjZ7io5Msw");

        Map<String, Object> images = new HashMap<>();
        Map<String, Object> original = new HashMap<>();
        original.put("url", "https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy.gif&ct=g");
        original.put("size", "466377");
        images.put("original", original);
        data1.put("images", images);
        data.add(data1);

        Map<String, Integer> pagination = new HashMap<>();
        pagination.put("total_count", 10907);
        pagination.put("count", 1);
        pagination.put("offset", 0);

        Map<String, Object> meta = new HashMap<>();
        meta.put("status", 200);
        meta.put("msg", "OK");
        meta.put("response_id", "xpihltsv8xyngih4vb4r53sxquho9x8pub793ezr");

        GifExternalDto gifDto = new GifExternalDto();
        gifDto.setData(data);
        gifDto.setPagination(pagination);
        gifDto.setMeta(meta);
        gifData = mapper.writeValueAsString(gifDto);

        Map<String, Double> latest = new HashMap<>();
        latest.put("KZT", 460.23);
        CurrencyExternalDto latestDto = new CurrencyExternalDto();
        latestDto.setBase("USD");
        latestDto.setRates(latest);
        latestRate = mapper.writeValueAsString(latestDto);

        Map<String, Double> historical = new HashMap<>();
        historical.put("KZT", 459.87);
        CurrencyExternalDto historicalDto = new CurrencyExternalDto();
        historicalDto.setBase("USD");
        historicalDto.setRates(historical);
        historicalRate = mapper.writeValueAsString(historicalDto);
    }

    @Test
    public void testGetGifOnRate() throws IOException {
        when(externalCurrencyService.getLatestRates(any(), any())).thenReturn(latestRate);
        when(externalCurrencyService.getHistorical(any(),any(), any())).thenReturn(historicalRate);
        when(externalGifService.searchGifsByKeyWord(any(), any())).thenReturn(gifData);

        byte[] kztGif = service.getGifOnRate("KZT");
        byte[] expected = Files.readAllBytes(Path.of("src/test/resources/test.webp"));

        assertEquals(expected.length, kztGif.length);
    }
}
