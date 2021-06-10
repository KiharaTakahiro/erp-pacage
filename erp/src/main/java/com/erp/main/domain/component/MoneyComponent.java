package com.erp.main.domain.component;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.main.app.controller.system.response.BaseResponse;
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
	
	public static final String TAX = "TAX";
	
	/**
	 * 金額に対する消費税の計算を行う
	 * @return
	 */
	public long computeTax(long target) {
		/*
		 * TODO: マスタ等から取得するように変更するべき
		 * リポジトリでデータを引いてきて、数字化して1/100にして変数に
		 * Keyは変数にして大文字にする
		 * private static string final クラスに書く
		 */
		Optional<SystemEntity> taxEntity = this.systemRepository.findById(TAX);
		String taxVal = taxEntity.get().getValue();
		double beforTax =  Double.parseDouble(taxVal.trim());		
		double tax = beforTax / 100;
		
		return Double.doubleToLongBits(target * tax);
	}

}
