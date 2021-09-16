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
public class WarehouseResponse extends BaseResponse{
	//最大ページ数
	private Integer maxpage;
	
	// 倉庫一覧
	List <WarehouseModel> warehouse;
}
