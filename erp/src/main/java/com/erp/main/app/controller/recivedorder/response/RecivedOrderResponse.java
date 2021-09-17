package com.erp.main.app.controller.recivedorder.response;

import java.util.ArrayList;
import java.util.List;

import com.erp.main.app.controller.system.response.BaseResponse;
import com.erp.main.domain.objects.model.RecivedOrderDetailModel;
import com.erp.main.domain.objects.valueobjects.GetRecivedOrderVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RecivedOrderResponse extends BaseResponse{
	// 受注Seq
	private Long recivedOrderSeq;
	//取引先Seq
	private Long clientsSeq;
	//会社Seq
	private Long companySeq;
	//部署Seq
	private Long departmentSeq;
	//見積Seq
	private Long quotationSeq;
	//受注日
	private String recivedOrderDate;
	//税金
	private Long tax;
	//合計金額
	private Long total;
	//受注詳細
	private List<RecivedOrderDetailResponse> details;
	
	@Data
	public static class RecivedOrderDetailResponse {
		//受注詳細Seq
		private Long recivedOrderDetailSeq;
		//受注Seq
		private Long recivedOrderSeq;
		// 配送日
		private String deriveryDate;
		// 値引
		private Long discount;
		// ロットSEQ
		private Long lotSeq;
		// 商品SEQ
		private Long productSeq;
		// 数量
		private Integer quantity;
		// 状態
		private Integer status;
		
	}
	
	public static RecivedOrderResponse mapTo(GetRecivedOrderVo vo) {
		var response = new RecivedOrderResponse();
		// 受注Vo
		var recivedOrder = vo.getRecivedOrder();
		//受注Seq
		response.setRecivedOrderSeq(recivedOrder.getRecivedOrderSeq());
		//取引先Seq
		response.setClientsSeq(recivedOrder.getClientsSeq());
		//会社Seq
		response.setCompanySeq(recivedOrder.getCompanySeq());
		//部署Seq
		response.setDepartmentSeq(recivedOrder.getDepartmentSeq());
		//見積Seq
		response.setQuotationSeq(recivedOrder.getQuotationSeq());
		//受注日
		response.setRecivedOrderDate(recivedOrder.getRecivedOrderDate());
		//税金
		response.setTax(recivedOrder.getTax());
		//合計金額
		response.setTotal(recivedOrder.getTotal());
		
		List<RecivedOrderDetailResponse> detailsResponse = new ArrayList<>();
		
		// 受注詳細Vo
		var details = vo.getDetails();
		for(RecivedOrderDetailModel detail: details) {
			var detailResponse = new RecivedOrderDetailResponse();
			//受注詳細Seq
			detailResponse.setRecivedOrderDetailSeq(detail.getRecicedOrderDetailSeq());
			//受注Seq
			detailResponse.setRecivedOrderSeq(detail.getRecivedOrderSeq());
			//配送日
			detailResponse.setDeriveryDate(detail.getDeriveryDate());
			//値引
			detailResponse.setDiscount(detail.getDiscount());
			//ロットSeq
			detailResponse.setLotSeq(detail.getLotSeq());
			//商品Seq
			detailResponse.setProductSeq(detail.getProductSeq());
			//個数
			detailResponse.setQuantity(detail.getQuantity());
			//状態
			detailResponse.setStatus(detail.getStatus().getCode());
			//詳細をリストに追加
			detailsResponse.add(detailResponse);
		
		}
		//レスポンスに詳細追加
		response.setDetails(detailsResponse);
		return response;
	}
}
