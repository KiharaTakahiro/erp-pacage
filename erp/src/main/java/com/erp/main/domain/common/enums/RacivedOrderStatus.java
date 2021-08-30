package com.erp.main.domain.common.enums;

import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * 受注ステータス
 * @author takah
 *
 */
public enum RacivedOrderStatus implements BaseEnum<RacivedOrderStatus> {
	
	READY(1,"受注準備中"),
	ORDERED(2,"受注済み"),
	DERIVERY(3,"納品済み"),
	CANCEL(4,"失注");
	
	// コード
	private Integer code;
	
	// 表示名
	private String dispName;
	
	/**
	 * コンストラクタ
	 * @param code
	 * @param dispName
	 */
	private RacivedOrderStatus(Integer code, String dispName) {
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
	public List<RacivedOrderStatus> getEnums() {
		return Arrays.asList(READY,ORDERED,DERIVERY,CANCEL);
	}
	
	/**
	 * 発注ステータスのコンバータ
	 * @author takah
	 *
	 */
	@Converter
	public class RacivedOrderStatusConverter implements AttributeConverter<RacivedOrderStatus, Integer> {

		/**
		 * Enum → codeへの変換
		 */
		@Override
		public Integer convertToDatabaseColumn(RacivedOrderStatus attribute) {
			return attribute == null ? null: attribute.getCode();
		}

		/**
		 * code -> Enumへの変換
		 */
		@Override
		public RacivedOrderStatus convertToEntityAttribute(Integer dbData) {
			return dbData == null? null : BaseEnum.of(RacivedOrderStatus.class, dbData);
		}
		
	}
}
