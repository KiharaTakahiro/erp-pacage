package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.GetClientsConditionsVo;

import lombok.Data;

/*
 * 取引先取得のリクエスト
 * @author ngt
 */
@Data
public class GetClientsRequest {
	// ページNo
	private Integer pageNo;
    // 取引先SEQ
    private Long clientsSeq;
    
    // 取引先名
    private String clientsName;
    
    /**
     * リクエスト空ValueObjectへのマッピング
     * @return
     */
    public GetClientsConditionsVo mapTo() {
        var vo = new GetClientsConditionsVo();
        // ページ番号
        vo.setPageNo(this.getPageNo());
        // 取引先SEQ
        vo.setClientsSeq(this.getClientsSeq());
        // 取引先名
        vo.setClientsName(this.getClientsName());
        return vo;
    }

}
