package org.rlrio.service.currency.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ExceptionResponseDto {

    private String message;
    private LocalDateTime timestamp;

    public ExceptionResponseDto(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}