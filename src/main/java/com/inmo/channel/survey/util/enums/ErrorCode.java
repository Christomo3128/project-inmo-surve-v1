package com.inmo.channel.survey.util.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  BAD_REQUEST_EXCEPTION("IY00403", HttpStatus.BAD_REQUEST),
  SERVER_ERROR_EXCEPTION("IY00404", HttpStatus.INTERNAL_SERVER_ERROR),
  SERVER_ERROR_THROWABLE("IY00405", HttpStatus.INTERNAL_SERVER_ERROR),
  DATABASE_COMPONENT("IY00406", HttpStatus.INTERNAL_SERVER_ERROR);

  private String code;

  private HttpStatus httpStatus;

}
