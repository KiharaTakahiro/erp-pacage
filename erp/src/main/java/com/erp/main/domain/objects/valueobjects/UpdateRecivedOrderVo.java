package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.RecivedOrderDetailModel;
import com.erp.main.domain.objects.model.RecivedOrderModel;

import lombok.Data;
@Data
public class UpdateRecivedOrderVo {
	//受注票モデル
	private RecivedOrderModel recivedOrder;
	private List<UpdateRecivedOrderDetailVo> details;
	//受注詳細モデル
	@Data
	public static class UpdateRecivedOrderDetailVo {
		private RecivedOrderDetailModel detail;
	}
	
}
