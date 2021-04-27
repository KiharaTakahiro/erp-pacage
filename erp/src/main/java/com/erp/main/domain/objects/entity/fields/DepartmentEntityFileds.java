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
 * 部署を管理する
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class DepartmentEntityFileds extends BaseEntityFields {

	/**
	 * 部署SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "department_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "DEPARTMENT_SEQ")
	private Long departmentSeq;

	/**
	 * 会社SEQ
	 */
	@Column(name = "COMPANY_SEQ")
	private Long companySeq;
	
	/**
	 * 親部署名称
	 */
	@Column(name = "PARENT_DEPARTMENT_SEQ")
	private Long parentDepartmentSeq;
	
	/**
	 * 部署名称
	 */
	@Column(name = "NAME")
	private String name;

}
