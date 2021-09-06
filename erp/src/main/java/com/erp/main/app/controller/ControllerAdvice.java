package com.erp.main.app.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * コントローラの横断的な処理を記載するクラス
 * @author takah
 *
 */
@RestControllerAdvice
@Slf4j
public class ControllerAdvice {


	@InitBinder
	public void initBuilder(WebDataBinder dataBinder) {
		
		log.debug("initBinder : {}",dataBinder);
	}

	
	@ExceptionHandler
	public String handleException(Exception e) {
		log.error(e.getMessage());
		return "";
	}
}
