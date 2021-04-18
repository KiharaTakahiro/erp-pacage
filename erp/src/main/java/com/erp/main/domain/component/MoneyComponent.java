package com.erp.main.domain.component;

import org.springframework.stereotype.Component;

/**
 * 金額計算に関連するコンポーネント
 * @author takah
 *
 */
@Component
public class MoneyComponent {
	
	/**
	 * 金額に対する消費税の計算を行う
	 * @return
	 */
	public long computeTax(long target) {
		// TODO: マスタ等から取得するように変更するべき
		return Double.doubleToLongBits(target * 0.1);
	}

}