package com.erp.main.domain.objects.valueobjects;

import java.util.ArrayList;
import java.util.List;

import com.erp.main.domain.objects.entity.RecivedOrderDetailEntity;
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
		//受注Seq
		model.setRecivedOrderSeq(entity.getRecivedOrderSeq());
		//取引先Seq
		model.setClientsSeq(entity.getClientsSeq());
		//会社Seq
		model.setCompanySeq(entity.getCompanySeq());
		//部署Seq
		model.setDepartmentSeq(entity.getDepartmentSeq());
		//受注日
		model.setRecivedOrderDate(entity.getRecivedOrderDate());
		//合計金額
		model.setTotal(entity.getTotal());
		//税合計
		model.setTax(entity.getTax());
		
		
		
		// 値格納用のリスト作成
		List<RecivedOrderDetailModel> detailList =  new ArrayList<>();
		//詳細取得
		var details = entity.getRecivedOrderDetailEntity();
		//各値を変換
		for(RecivedOrderDetailEntity detail: details) {
			var detailModel = new RecivedOrderDetailModel();
			//受注詳細Seq
			detailModel.setRecicedOrderDetailSeq(detail.getRecivedOrderDetailSeq());
			//受注Seq
			detailModel.setRecivedOrderSeq(detail.getRecivedOrderSeq());
			//配送日
			detailModel.setDeriveryDate(detail.getDeriveryDate());
			//値引
			detailModel.setDiscount(detail.getDiscount());
			//ロットSeq
			detailModel.setLotSeq(detail.getLotSeq());
			//商品Seq
			detailModel.setProductSeq(detail.getProductSeq());
			//個数
			detailModel.setQuantity(detail.getQuantity());
			//配送状態
			detailModel.setStatus(detail.getStatus());
			//リストに追加
			detailList.add(detailModel);
		}
		
		// Voを生成
		var vo = new GetRecivedOrderVo();
		vo.setRecivedOrder(model);
		vo.setDetails(detailList);
		
		return vo;
	}
}