package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * ロット
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class LotEntityFileds extends BaseEntityFields {

	/**
	 * ロットSEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "lot_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "LOT_SEQ")
	private Long lotSeq;

	/**
	 * ロット番号
	 */
	@Column(name = "LOT_NO")
	private String lotNo;
	
	/**
	 * 製造年月日
	 */
	@Column(name = "LOT_DATE")
	private String lotDate;
}
