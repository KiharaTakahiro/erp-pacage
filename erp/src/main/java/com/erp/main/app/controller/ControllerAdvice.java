package com.erp.main.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
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
	

//	@InitBinder
//	public void requestLog(@RequestBody HttpServletRequest request) throws IOException{
//		BufferedReader reader = request.getReader();
//	    Stream<String> lines = reader.lines();
//	    String result = lines.collect(Collectors.joining("\r\n"));
//		log.info("テスト" + result);
//		
//	}
//	


//	@InitBinder
//	public void requestLog2(@RequestBody HttpServletRequest request){
//	
//		log.info("テスト" + IOUtils.toString(request.getReader()));
//		
//	}
	
	@ExceptionHandler
	public String handleException(Exception e) {
		return "";
	}
}
