package com.erp.main.domain.common.enums;

import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * 発注ステータス
 * @author takah
 *
 */
public enum OrderStatus implements BaseEnum<OrderStatus> {
	
	READY(1,"発注準備中"),
	ORDERED(2,"発注済み"),
	CANCEL(3,"発注キャンセル");
	
	// コード
	private Integer code;
	
	// 表示名
	private String dispName;
	
	/**
	 * コンストラクタ
	 * @param code
	 * @param dispName
	 */
	private OrderStatus(Integer code, String dispName) {
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
	public List<OrderStatus> getEnums() {
		return Arrays.asList(READY,ORDERED,CANCEL);
	}
	
	/**
	 * 発注ステータスのコンバータ
	 * @author takah
	 *
	 */
	@Converter
	public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

		/**
		 * Enum → codeへの変換
		 */
		@Override
		public Integer convertToDatabaseColumn(OrderStatus attribute) {
			return attribute == null ? null: attribute.getCode();
		}

		/**
		 * code -> Enumへの変換
		 */
		@Override
		public OrderStatus convertToEntityAttribute(Integer dbData) {
			return dbData == null? null : BaseEnum.of(OrderStatus.class, dbData);
		}
		
	}
}
