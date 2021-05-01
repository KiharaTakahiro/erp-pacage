package com.erp.main.domain.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.objects.entity.QuotationDetailEntity;
import com.erp.main.domain.objects.entity.QuotationEntity;
import com.erp.main.domain.objects.valueObjects.CreateQuotationVo;
import com.erp.main.domain.repository.QuotationRepository;

/**
 * 見積のサービス
 * @author 木原
 *
 */
@Service
public class QuotationService {
	
	/**
	 * 見積リポジトリ
	 */
	@Autowired
	private QuotationRepository quotationRepository;

	/**
	 * 見積詳細作成
	 * @param createQuotationVo
	 */
	@Transactional
	public void createQuotation(CreateQuotationVo createQuotationVo) {
		
		// VOからマッピング可能な部分をマッピング
		QuotationEntity quotation = QuotationEntity.create(createQuotationVo);
		// FIXME: 小計,合計,消費税の計算が行えていない
		// 小計
		quotation.setSubTotal(0L);
		// 合計
		quotation.setTotal(0L);
		// 消費税
		quotation.setTax(0L);
		Set<QuotationDetailEntity> details = createQuotationVo.getDetails()
				.stream()
				.map(QuotationDetailEntity::create)
				.collect(Collectors.toSet());
		quotation.setQuotationDetail(details);
		quotation = this.quotationRepository.save(quotation);
	}
}
