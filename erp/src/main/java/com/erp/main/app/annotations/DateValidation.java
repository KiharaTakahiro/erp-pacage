package com.erp.main.app.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
public @interface DateValidation {
	String message() default "{0}は日付として許可された形式ではありません。";
	Class<?>[]groups() default {};
    Class<? extends Payload>[] payload() default {};
	
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.LOCAL_VARIABLE})
    @Documented
     public @interface List {
             DateValidation[] value();
     }
}


