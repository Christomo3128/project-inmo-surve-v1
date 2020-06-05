package com.inmo.channel.survey.util.exceptions;

import lombok.Getter;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import com.inmo.channel.survey.util.enums.ErrorCode;

@Getter
public abstract class InmoException extends Exception {

	public InmoException(String msg) {
		super(msg);
	}

	public abstract ErrorCode getErrorCode();

}