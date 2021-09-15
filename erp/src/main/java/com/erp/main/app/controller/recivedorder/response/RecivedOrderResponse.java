package com.erp.main.app.controller.recivedorder.response;

import java.util.List;

import com.erp.main.app.controller.system.response.BaseResponse;
import com.erp.main.domain.objects.model.RecivedOrderDetailModel;
import com.erp.main.domain.objects.model.RecivedOrderModel;
import com.erp.main.domain.objects.valueobjects.GetRecivedOrderVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RecivedOrderResponse extends BaseResponse{
	//受注詳細
	private RecivedOrderModel recivedOrder;
	//受注詳細モデル
	private List<RecivedOrderDetailModel> details;
	
	public static RecivedOrderResponse mapTo(GetRecivedOrderVo vo) {
		var recivedOrder = vo.getRecivedOrder();
		var details = vo.getDetails();
		var response = new RecivedOrderResponse();
		//受注
		response.setRecivedOrder(recivedOrder);
		//詳細
		response.setDetails(details);
		

		return response;
	}
}
