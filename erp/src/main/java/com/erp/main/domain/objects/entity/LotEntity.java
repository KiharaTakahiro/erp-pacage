package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.LotEntityFileds;


/**
 * ロット
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Lot")
public class LotEntity extends LotEntityFileds {

	
}
