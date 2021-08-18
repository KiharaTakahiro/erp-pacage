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
//
//	@InitBinder
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    String result = request.getReader().lines().collect(Collectors.joining("\r\n"));
//	    log.debug("てすと：{}", result);
//	}
	

	
//	@InitBinder
//	public void requestLog2(HttpServletRequest request) throws IOException{
//	
//		log.debug("てすと：{}", request.getHeaderNames());
//	}
	
	@ExceptionHandler
	public String handleException(Exception e) {
		return "";
	}
}
