package com.erp.main.app.annotations.datevalidation;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * 日付のバリデーションの実行クラス
 * @author ngt
 */

public abstract class DateValidatelmp  implements ConstraintValidator<DateValidation, String> {

	@Override
    public void initialize(DateValidation constraintAnnotation) {
     	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null){
            return true;
		}
       
		Pattern ptn = Pattern.compile("^(\\d{4})(\\d{1,2})(\\d{1,2})$");
		Matcher mch = ptn.matcher(value);
		if(mch.find()){
              try {
                       LocalDate.of(Integer.valueOf(mch.group(1)), Integer.valueOf(mch.group(2)), Integer.valueOf(mch.group(3)));
               } catch (Exception e) {
                      return false;
               }
		}else{
             return false;
		}

		return true;
	}
}
