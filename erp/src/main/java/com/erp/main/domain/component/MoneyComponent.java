package com.erp.main.domain.component;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.main.app.controller.system.response.BaseResponse;
import com.erp.main.domain.common.enums.TaxType;
import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.objects.entity.SystemEntity;
import com.erp.main.domain.repository.SystemRepository;

/**
 * 金額計算に関連するコンポーネント
 * @author takah
 *
 */
@Component
public class MoneyComponent extends BaseResponse {
	
	/**
	 * システムリポジトリ
	 */
	@Autowired
	private SystemRepository systemRepository;
	
	// 税金なしのシステムリポジトリキー
	private static final String NO_TAX = "NO_TAX";
	// 軽減税率のシステムリポジトリのキー
	private static final String REDUCED_RATE_TAX = "REDUCED_RATE_TAX";
	// 通常の税金のシステムリポジトリのキー
	private static final String NOMAL_TAX  = "NOMAL_TAX";
	
	/**
	 * 金額に対する消費税の計算を行う
	 * @return
	 */
	public Long computeTax(long target, TaxType taxType) {

		String taxCase;

		switch(taxType){
			case NO_TAX:
				taxCase = NO_TAX;
				break;
			case REDUCED_RATE:
				taxCase = REDUCED_RATE_TAX;
				break;
			case NOMAL:
				taxCase = NOMAL_TAX;
				break;
			default:
				throw new AppException((String.format("想定外の税区分です。。 taxVal: %s", taxType)));
		}
		
		
		Optional<SystemEntity> taxEntity = this.systemRepository.findById(taxCase);
		if(taxEntity.isEmpty()) {
			throw new AppException(String.format("該当の税区分を取得できませんでした。 taxVal: %s", taxCase));
		}
		String taxVal = taxEntity.get().getValue();
		var beforTax =  Double.parseDouble(taxVal.trim());		
		var tax = beforTax / 100;
		
		return Math.round(target * tax);
	}

}
