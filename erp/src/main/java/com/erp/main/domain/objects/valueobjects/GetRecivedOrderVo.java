package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.RecivedOrderModel;

import lombok.Data;

/*
 * 受注用のVO
 * @author ngt
 */

@Data
public class GetRecivedOrderVo {
	//受注リスト
	List<RecivedOrderModel> recivedOder;
	
	// 全体のページ数
	Long totalItemsNum;;
}
