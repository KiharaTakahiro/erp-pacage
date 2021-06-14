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
	
	@Autowired
	private SystemRepository systemRepository;
	
	public static final String NO_TAX = "NO_TAX";
	public static final String REDUCED_RATE_TAX = "REDUCED_RATE_TAX";
	public static final String NOMAL_TAX  = "NOMAL_TAX";
	
	/**
	 * 金額に対する消費税の計算を行う
	 * @return
	 */
	public Long computeTax(long target, TaxType taxType) {
		/*
		 * TODO: マスタ等から取得するように変更するべき
		 * リポジトリでデータを引いてきて、数字化して1/100にして変数に
		 * Keyは変数にして大文字にする
		 * private static string final クラスに書く
		 * swich文で税率を変える
		 */
		String TaxVal = null;

		switch(taxType){
			case NO_TAX:
				TaxVal = NO_TAX;
				break;
			case REDUCED_RATE:
				TaxVal = REDUCED_RATE_TAX;
				break;
			case NOMAL:
				TaxVal = NOMAL_TAX;
				break;
		}
		
		
		Optional<SystemEntity> taxEntity = this.systemRepository.findById(TaxVal);
		if(taxEntity.isEmpty()) {
			throw new AppException(String.format("該当の税区分を取得できませんでした。 TAX: %s", TaxVal));
		}
		String taxVal = taxEntity.get().getValue();
		var beforTax =  Double.parseDouble(taxVal.trim());		
		var tax = beforTax / 100;
		
		return Math.round(target * tax);
	}

}
