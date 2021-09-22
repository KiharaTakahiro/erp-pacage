package com.erp.main.app.controller.recivedorder.requests;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.erp.main.domain.common.enums.RacivedOrderStatus;
import com.erp.main.domain.objects.model.RecivedOrderDetailModel;
import com.erp.main.domain.objects.model.RecivedOrderModel;
import com.erp.main.domain.objects.valueobjects.UpdateRecivedOrderVo;
import com.erp.main.domain.objects.valueobjects.UpdateRecivedOrderVo.UpdateRecivedOrderDetailVo;

import lombok.Data;

/*
 * 受注票更新用のリクエスト
 * @author ngt
 */
@Data
public class UpdateRecivedOrderRequest {
	//受注Seq
	private Long recivedOrderSeq;
	
	//取引先Seq
	private Long clientsSeq;
	
	// 会社SEQ
	private Long companySeq;
	
	// 部署SEQ
	private Long departmentSeq;
	
	// 見積SEQ
	private Long quotationSeq;
	
	// 受注日
	private String recivedOrderDate;
	
	// 消費税
	@NotNull
	private Long tax;
	
	// 合計金額
	@NotNull
	private Long total;
	
	// 受注詳細
	private List<UpdateRecivedOrderDetailRequest> details;
	
	/**
	 * 受注詳細のリクエスト
	 * @author ngt
	 *
	 */
	@Data
	public static class UpdateRecivedOrderDetailRequest {
		
		//受注詳細Seq
		private Long recivedOrderDetailSeq;
		
		//受注Seq
		private Long recivedOrderSeq;
		
		// 配送日
		@NotNull
		private String deriveryDate;
		
		// 値引
		@NotNull
		private Long discount;
		
		// ロットSEQ
		@NotNull
		private Long lotSeq;
		
		// 商品SEQ
		@NotNull
		private Long productSeq;
		
		// 数量
		@NotNull
		private Integer quantity;
		
		// 状態
		@NotNull
		private RacivedOrderStatus status;
				
	}
	
	/*
	 * リクエストからVoへのマッピング
	 * @author ngt
	 */
	public UpdateRecivedOrderVo mapTo() {
		
		var recivedOrder = new RecivedOrderModel();
		//
		recivedOrder.setRecivedOrderSeq(this.getRecivedOrderSeq());
		//
		recivedOrder.setClientsSeq(this.getClientsSeq());
		//
		recivedOrder.setCompanySeq(this.getCompanySeq());
		//
		recivedOrder.setDepartmentSeq(this.getDepartmentSeq());
		//
		recivedOrder.setQuotationSeq(this.getQuotationSeq());
		//
		recivedOrder.setRecivedOrderDate(this.getRecivedOrderDate());
		//
		recivedOrder.setTax(this.getTax());
		//
		recivedOrder.setTotal(this.getTotal());
		
		
		//
		List<UpdateRecivedOrderDetailVo> detailList = new ArrayList<>();
		
		for(UpdateRecivedOrderDetailRequest detail: this.getDetails()) {
			var detailVo = new UpdateRecivedOrderDetailVo();
			var model = new RecivedOrderDetailModel();
			//
			model.setRecicedOrderDetailSeq(detail.getRecivedOrderDetailSeq());
			//
			model.setRecivedOrderSeq(detail.getRecivedOrderSeq());
			//
			model.setDeriveryDate(detail.getDeriveryDate());
			//
			model.setDiscount(detail.getDiscount());
			//
			model.setLotSeq(detail.getLotSeq());
			//
			model.setProductSeq(detail.getProductSeq());
			//
			model.setQuantity(detail.getQuantity());
			//
			model.setStatus(detail.getStatus());
			
			detailVo.setDetail(model);
			//
			detailList.add(detailVo);
		}
		
		var vo = new UpdateRecivedOrderVo();
		vo.setRecivedOrder(recivedOrder);
		vo.setDetails(detailList);
		
		return vo;
	}
	

}
