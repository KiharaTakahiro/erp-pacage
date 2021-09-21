package com.erp.main.app.controller.system.response;

import java.util.List;

import com.erp.main.domain.objects.model.WarehouseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 倉庫一覧レスポンス
 * @author 高山
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class WarehousesResponse extends BaseResponse{
	// 全データの総数
	private Long totalItemsNum;
	// 倉庫一覧
	List <WarehouseModel> warehouse;
}
