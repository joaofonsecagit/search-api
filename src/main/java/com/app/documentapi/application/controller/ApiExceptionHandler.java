package com.app.documentapi.application.controller;

import com.app.documentapi.application.exception.DocumentReadingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError handleException(Exception exception) {
    log.error("Unexpected exception with message: {}", exception.getMessage(), exception);
    return ApiError.builder()
        .code("UNKNOWN ERROR")
        .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        .message("Unexpected error")
        .build();
  }

  @ResponseBody
  @ExceptionHandler(value = {DocumentReadingException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError handleException(DocumentReadingException exception) {
    log.error("Exception while reading file with message: {}", exception.getMessage(), exception);
    return ApiError.builder()
        .code("FILE READING ERROR")
        .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        .message("Error")
        .build();
  }
}
