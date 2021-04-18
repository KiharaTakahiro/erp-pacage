package com.erp.main.domain.common.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * アプリケーション内でエラーが発生した場合のException
 * 
 * @author 木原
 *
 */
@Slf4j
public class AppException extends RuntimeException{

    public AppException(String message) {
        super(message);
    	log.error(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    	log.error(message);        
    }

}
