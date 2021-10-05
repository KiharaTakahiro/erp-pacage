package com.erp.main.app.controller.recivedorder.response;

import java.util.List;

import com.erp.main.app.controller.system.response.BaseResponse;
import com.erp.main.domain.objects.model.SupplierModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仕入先一覧レスポンス
 * @author 高山
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SuppliersResponse extends BaseResponse{
	// 全データの総数
	private Long totalItemsNum;
	// 仕入先一覧
	List <SupplierModel> supplier;
}
