package com.erp.main.domain.common.enums;

import java.util.Arrays;
import java.util.List;

public interface BaseEnum<E extends Enum<E>> {
	
	/**
	 * コード値を返却する
	 * @return
	 */
	public Integer getCode();
	
	/**
	 * ディスプレイの名称を返却する
	 * @return
	 */
	public String getDisplayName();
    
	/**
	 * すべてのEnumを返却する処理を実装する
	 * @return
	 */
	public List<E> getEnums();
	
	/**
	 * コード値をもとにEnumを生成するメソッド
	 * @param <E>
	 * @param enumClass
	 * @param code
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <E extends BaseEnum> E of(Class<E> enumClass, int code) {
        return Arrays.stream(enumClass.getEnumConstants()) 
                     .filter(it -> it.getCode() == code) 
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException(String.format("%s 該当のコードが見つかりません。 => [%s]",
                                                                                   enumClass.getSimpleName(),
                                                                                   code)));
    }
	
}
