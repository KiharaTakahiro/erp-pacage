package com.erp.main.domain.common.enums;

import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 税区分
 * @author takah
 *
 */
public enum TaxType implements BaseEnum<TaxType> {
	
	NO_TAX(1,"税金なし"),
	REDUCED_RATE(2,"軽減税率"),
	NOMAL(3,"通常");
	
	// コード
	private Integer code;
	
	// 表示名
	private String dispName;
	
	/**
	 * コンストラクタ
	 * @param code
	 * @param dispName
	 */
	private TaxType(Integer code, String dispName) {
		this.code = code;
		this.dispName = dispName;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getCode() {
		return this.code;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDisplayName() {
		return this.dispName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TaxType> getEnums() {
		return Arrays.asList(NO_TAX,REDUCED_RATE,NOMAL);
	}
	
	/**
	 * 税区分のコンバータ
	 * @author takah
	 *
	 */
	@Converter
	public class TaxTypeConverter implements AttributeConverter<TaxType, Integer> {

		/**
		 * Enum → codeへの変換
		 */
		@Override
		public Integer convertToDatabaseColumn(TaxType attribute) {
			return attribute == null ? null: attribute.getCode();
		}

		/**
		 * code -> Enumへの変換
		 */
		@Override
		public TaxType convertToEntityAttribute(Integer dbData) {
			return dbData == null? null : BaseEnum.of(TaxType.class, dbData);
		}
		
	}
}
