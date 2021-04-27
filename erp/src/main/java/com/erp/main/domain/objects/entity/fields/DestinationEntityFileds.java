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
 * 出荷先
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class DestinationEntityFileds extends BaseEntityFields {

	/**
	 * 出荷先SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "destination_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "DESTINATION_SEQ")
	private Long destinationSeq;

	/**
	 * 取引先SEQ
	 */
	@Column(name = "CLIENTS_SEQ")
	private Long clientsSeq;

	
	/**
	 * 出荷先名称
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 出荷先住所
	 */
	@Column(name = "ADDRESS")
	private String address;
	
}
