package com.inmo.channel.survey.util.exceptions;

import com.inmo.channel.survey.util.enums.ErrorCode;

public class DatabaseComponent extends InmoException {

  private static final ErrorCode errorCode = ErrorCode.DATABASE_COMPONENT;

  public DatabaseComponent() {
    super(errorCode.getCode());
  }

  @Override
  public ErrorCode getErrorCode() {
    return errorCode;
  }

}
