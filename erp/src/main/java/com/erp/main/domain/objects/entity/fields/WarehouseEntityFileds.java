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
 * 倉庫
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class WarehouseEntityFileds extends BaseEntityFields {

	/**
	 * 倉庫SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "warehouse_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "WAREHOUSE_SEQ")
	private Long warehouseSeq;

	/**
	 * 倉庫名
	 */
	@Column(name = "NAME")
	private String name;
	
}
