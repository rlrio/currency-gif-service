package org.rlrio.service.currency.controller;

import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rlrio.service.currency.dto.ExceptionResponseDto;
import org.rlrio.service.currency.exception.CurrencyGifException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CurrencyGifControllerExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(CurrencyGifControllerExceptionHandler.class);

    @ExceptionHandler(CurrencyGifException.class)
    public ResponseEntity<ExceptionResponseDto> handleCurrencyGifException(CurrencyGifException e) {
        LOGGER.error(e.getMessage(), e);

        return new ResponseEntity<>(
                new ExceptionResponseDto(e.getMessage(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        LOGGER.error(e.getMessage(), e);

        return new ResponseEntity<>(
                new ExceptionResponseDto(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
