package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.entity.RecivedOrderEntity;
import com.erp.main.domain.objects.model.RecivedOrderDetailModel;
import com.erp.main.domain.objects.model.RecivedOrderModel;

import lombok.Data;

/*
 * 受注詳細取得用VO
 * @author ngt
 */
@Data
public class GetRecivedOrderVo {
	
	//受注票モデル
	private RecivedOrderModel recivedOrder;
	//受注詳細モデル
	private List<RecivedOrderDetailModel> details;
	
	/*
	 * エンティティから受注Voへのマッピング
	 */
	public static GetRecivedOrderVo mapTo(RecivedOrderEntity entity) {
		var model = new RecivedOrderModel();
		//
		model.setRecivedOrderSeq(entity.getRecivedOrderSeq());
		//
		model.setClientsSeq(entity.getClientsSeq());
		//
		model.setCompanySeq(entity.getCompanySeq());
		//
		model.setDepartmentSeq(entity.getDepartmentSeq());
		//
		model.setRecivedOrderDate(entity.getRecivedOrderDate());
		//
		model.setTotal(entity.getTotal());
		//
		model.setTax(entity.getTax());
		
		// 
		var vo = new GetRecivedOrderVo();
		vo.setRecivedOrder(model);
		
		return vo;
		
	}
}
