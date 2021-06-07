package com.erp.main.app.controller.system.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ClientsResponse extends BaseResponse {

	private Long clientsSeq;
	private String clientsName;
}
