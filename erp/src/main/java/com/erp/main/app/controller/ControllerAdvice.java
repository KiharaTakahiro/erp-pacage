package com.erp.main.app.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * コントローラの横断的な処理を記載するクラス
 * @author takah
 *
 */
@RestControllerAdvice
public class ControllerAdvice {

	  @ExceptionHandler
	  public String handleException(Exception e) {
	    return "";
	  }
}
