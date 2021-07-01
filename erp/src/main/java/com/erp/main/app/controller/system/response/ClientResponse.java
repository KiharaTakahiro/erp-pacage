package com.erp.main.app.controller.system.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 取引先一覧
 * @author ngt
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ClientResponse extends BaseResponse {
	
	private Long clientsSeq;
	private String clientsName;
}
