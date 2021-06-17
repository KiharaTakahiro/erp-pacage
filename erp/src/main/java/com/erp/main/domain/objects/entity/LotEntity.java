package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.LotEntityFileds;
import com.erp.main.domain.objects.valueObjects.CreateLotVo;


/**
 * ロット
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Lot")
public class LotEntity extends LotEntityFileds {
	
	public static LotEntity create(CreateLotVo vo) {
		var entity = new LotEntity();
		// ロットナンバー
		entity.setLotNo(vo.getLotNo());
		
		// 製造年月日
		entity.setLotDate(vo.getLotDate());
		
		return entity;
	}

	
}
