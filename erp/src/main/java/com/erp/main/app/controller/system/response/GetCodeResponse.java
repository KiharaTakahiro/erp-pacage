package com.erp.main.app.controller.system.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * コード取得用のレスポンス
 * @author takah
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class GetCodeResponse extends BaseResponse {

	// コード情報
	private List<Code> codes;
	
	/**
	 * コードの結果格納用
	 * @author takah
	 *
	 */
	@Data
	public static class Code {
		// EnumのKey
		private Integer key;
		// Enumの値
		private String value;
	}
	
	/**
	 * Mapからレスポンスを生成する
	 * @param map
	 */
	public static GetCodeResponse mapTo(Map<Integer, String> map) {
		var codes = new ArrayList<Code>();
		for(Map.Entry<Integer, String> entry : map.entrySet()) {
			var code = new Code();
			code.setKey(entry.getKey());
			code.setValue(entry.getValue());
			codes.add(code);
		}
		var reseponse = new GetCodeResponse();
		reseponse.setCodes(codes);
		return reseponse;
	}
}
