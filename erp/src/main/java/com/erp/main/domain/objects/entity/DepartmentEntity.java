package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.DepartmentEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateDepartmentVo;


/**
 * 部署
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Department")
public class DepartmentEntity extends DepartmentEntityFileds {

	/**
	 * 部署エンティティの生成
	 * @param vo
	 * @return
	 */
	public static DepartmentEntity create(CreateDepartmentVo vo) {
		
		var entity = new DepartmentEntity();
			
		// 部署SEQ
		entity.setCompanySeq(vo.getDepartmentCompanySeq());
		// 部署名
		entity.setName(vo.getDepartmentName());
		
		return entity;
	
	}
}
