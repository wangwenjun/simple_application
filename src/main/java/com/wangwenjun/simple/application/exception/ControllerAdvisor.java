package com.wangwenjun.simple.application.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
  private static final Logger LOG = LoggerFactory.getLogger(ControllerAdvisor.class);

  /**
   * Capture the ApiException and response the 500 internal error.
   *
   * @param ex      Exception object
   * @param request The original web request.
   * @return
   */
  @ExceptionHandler(ApiException.class)
  public ResponseEntity<Object> handleCityNotFoundException(
          ApiException ex, WebRequest request) {
    LOG.warn("Internal Exception", ex);
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", ex.getMessage());
    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}