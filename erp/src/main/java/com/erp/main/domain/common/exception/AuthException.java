package com.erp.main.domain.common.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 認証でエラーが発生した場合のException
 * 
 * @author 木原
 *
 */
@Slf4j
public class AuthException extends RuntimeException{

    public AuthException(Exception e) {
        super(e);
    	log.error(e.getMessage());
    }

}
