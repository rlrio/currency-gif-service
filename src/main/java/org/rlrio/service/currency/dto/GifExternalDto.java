package org.rlrio.service.currency.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class GifExternalDto {

    private List<Map<String, Object>> data;
    private Map<String, Integer> pagination;
    private Map<String, Object> meta;
}
