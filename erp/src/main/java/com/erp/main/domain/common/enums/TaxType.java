package com.erp.main.domain.common.enums;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


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
	
	public class TaxTypeSerializer extends JsonSerializer<TaxType> {

		public void serialize(TaxType value, JsonGenerator generator,
		            SerializerProvider provider) throws IOException,
		            JsonProcessingException {
		
		    generator.writeStartObject();
		    generator.writeFieldName("code");
			generator.writeNumber(value.getCode());
			generator.writeFieldName("disName");
		    generator.writeString(value.getDisplayName());
		    generator.writeEndObject();
		}
	}
}
