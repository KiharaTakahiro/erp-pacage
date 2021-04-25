package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.DepartmentEntityFileds;


/**
 * 部署
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Department")
public class DepartmentEntity extends DepartmentEntityFileds {

	
}
