package com.ps.cff.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author rranjan
 *
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class RestServiceException extends RuntimeException
{
  private static final long serialVersionUID = 1L;
 
  public RestServiceException(String message) {
        super(message);
    }
}
