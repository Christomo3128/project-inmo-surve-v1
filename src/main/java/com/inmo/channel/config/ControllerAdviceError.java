package com.inmo.channel.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

import com.inmo.channel.survey.util.enums.ErrorCode;
import com.inmo.channel.survey.util.exceptions.InmoException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerAdviceError {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler(InmoException.class)
	  public CustomErrorResponse exception(InmoException ex) {
	    log.error("CUSTOM EXCEPTION - INTERNAL_SERVER_ERROR: ", ex);
	    return CustomErrorResponse.builder()
	        .code(ex.getErrorCode().getCode()).build();
	  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ServerWebInputException.class)
  public CustomErrorResponse exception(ServerWebInputException ex) {
    log.error("EXCEPTION - BAD_REQUEST: ", ex);
    return CustomErrorResponse.builder()
        .code(ErrorCode.BAD_REQUEST_EXCEPTION.getCode()).build();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public CustomErrorResponse exception(Exception ex) {
    log.error("EXCEPTION - INTERNAL_SERVER_ERROR: ", ex);
    return CustomErrorResponse.builder()
        .code(ErrorCode.SERVER_ERROR_EXCEPTION.getCode()).build();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Throwable.class)
  public CustomErrorResponse exception(Throwable ex) {
    log.error("THROWABLE - INTERNAL_SERVER_ERROR: ", ex);
    return CustomErrorResponse.builder()
        .code(ErrorCode.SERVER_ERROR_THROWABLE.getCode()).build();
  }

}
