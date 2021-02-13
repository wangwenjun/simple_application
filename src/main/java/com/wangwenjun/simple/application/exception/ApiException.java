package com.wangwenjun.simple.application.exception;

public class ApiException extends RuntimeException {
  public ApiException(String message, Throwable e) {
    super(message, e);
  }
}
