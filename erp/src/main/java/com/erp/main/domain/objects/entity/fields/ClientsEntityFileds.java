package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 取引先を管理する
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class ClientsEntityFileds extends BaseEntityFields {

	/**
	 * 取引先SEQ
	 */
	@Id //PK
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable") //自動SEQ システムは不要
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "clients_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "CLIENTS_SEQ") //テーブルの名前
	private Long clientsSeq;

	/**
	 * 取引先名称
	 */
	@NotNull
	@Column(name = "NAME")
	private String name;
}
