package com.erp.main.domain.common.exception;

/**
 * アプリケーション内でエラーが発生した場合のException
 * 
 * @author 木原
 *
 */
public class AppException extends RuntimeException{

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

}
