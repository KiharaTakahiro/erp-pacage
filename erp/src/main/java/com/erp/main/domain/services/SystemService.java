package com.erp.main.domain.services;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.erp.main.domain.common.enums.BaseEnum;

/**
 * システム系の処理のサービス
 * @author 木原
 *
 */
@Service
public class SystemService {
	
	public static final String ENUM_PACKAGES = "com.erp.main.domain.common.enums.";
	
	/**
	 * Enumを指定すると該当Enumの情報を返却するメソッド
	 * @param codeType
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, String> getEnums(String codeType) throws ClassNotFoundException{
		@SuppressWarnings("rawtypes")
		Class<BaseEnum> cls = ((Class<BaseEnum>) Class.forName(new StringBuilder(ENUM_PACKAGES).append(codeType).toString()));
		return (Map<Integer, String>) Arrays
		.stream(cls.getEnumConstants())
		.collect(Collectors.toMap(BaseEnum::getCode, BaseEnum::getDisplayName));
		}
	
}

