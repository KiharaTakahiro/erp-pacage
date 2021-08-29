package com.erp.main.app.controller.system.request;

import com.erp.main.domain.common.enums.TaxType;
import com.erp.main.domain.objects.valueobjects.GetProductConditionsVo;

import lombok.Data;

/*
 * 商品取得のリクエスト
 * @author 高山
 */
@Data
public class GetProductsRequest {
	// ページNo
	private Integer pageNo;
    // 商品SEQ
    private Long productSeq;
    // 商品名
    private String productName;
    // 仕入料金
	private Long purchaseUnitPrice;
    // 定価
	private Long unitPrice;
	// 税区分
	private TaxType taxType;
	
    /**
     * リクエスト空ValueObjectへのマッピング
     * @return
     */
    public GetProductConditionsVo mapTo() {
        var vo = new GetProductConditionsVo();
        // ページ番号
        vo.setPageNo(this.getPageNo());
        // 取引先SEQ
        vo.setProductSeq(this.getProductSeq());
        // 取引先名
        vo.setProductName(this.getProductName());
        return vo;
    }
}
