package com.erp.main.app.controller.recivedorder.response;

import java.util.List;

import com.erp.main.app.controller.system.response.BaseResponse;
import com.erp.main.domain.objects.model.RecivedOrderModel;
import com.erp.main.domain.objects.valueobjects.GetRecivedOrderVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * 受注票一覧のレスポンス
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class RecivedOrderResponse extends BaseResponse {
	// 全データの総数
	private Long totalItemsNum;
	
	//受注モデル
	List<RecivedOrderModel> recivedOrder;

	public static RecivedOrderResponse mapTo(GetRecivedOrderVo vo) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
