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
 * 会社を管理する
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class CompanyEntityFileds extends BaseEntityFields {

	/**
	 * 会社SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "company_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "COMPANY_SEQ")
	private Long companySeq;

	/**
	 * 会社名
	 */
	@NotNull
	@Column(name="NAME")
	private String name;
	
	/**
	 * 会社郵便番号
	 */
	@NotNull
	@Column(name="ZIP")
	private String zip;
	
	/**
	 * 会社住所
	 */
	@NotNull
	@Column(name="ADDRESS")
	private String address;
	
	/**
	 * 会社電話番号
	 */
	@NotNull
	@Column(name="PHONE")
	private String phone;
}
