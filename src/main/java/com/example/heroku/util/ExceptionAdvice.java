package com.example.heroku.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice
{
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleException(final Exception e)
    {
        Map<String, Object> resBody = new HashMap<>();
        resBody.put("exception", e);
        log.error(e.toString());
        return new ResponseEntity<>(resBody, HttpStatus.BAD_REQUEST);
    }
}
