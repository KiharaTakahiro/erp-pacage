package com.erp.main.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.objects.entity.QuotationDetailEntity;
import com.erp.main.domain.objects.entity.QuotationEntity;
import com.erp.main.domain.objects.valueObjects.CreateQuotationVo;
import com.erp.main.domain.objects.valueObjects.CreateQuotationVo.CreateQuotationDetailVo;
import com.erp.main.domain.repository.QuotationDetailRepository;
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
	 * 見積詳細リポジトリ
	 */
	@Autowired
	private QuotationDetailRepository quotationDetailRepository;
	
	/**
	 * 見積詳細作成
	 * @param createQuotationVo
	 */
	@Transactional
	public void createQuotation(CreateQuotationVo createQuotationVo) {
		QuotationEntity quotation = createQuotationVo.create();
		quotation = quotationRepository.save(quotation);
		for(CreateQuotationDetailVo createQuotationDetail: createQuotationVo.getDetails()) {
			QuotationDetailEntity detail = createQuotationDetail.create(quotation.getQuotationSeq());
			quotationDetailRepository.save(detail);
		}
	}
}
