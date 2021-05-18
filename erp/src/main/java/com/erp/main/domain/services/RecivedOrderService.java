package com.erp.main.domain.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.CompanyEntity;
import com.erp.main.domain.objects.entity.DepartmentEntity;
import com.erp.main.domain.objects.entity.QuotationDetailEntity;
import com.erp.main.domain.objects.entity.QuotationEntity;
import com.erp.main.domain.objects.valueObjects.CreateRecivedOrderVo;
import com.erp.main.domain.repository.ClientsRepository;
import com.erp.main.domain.repository.CompanyRepository;
import com.erp.main.domain.repository.DepartmentRepository;
import com.erp.main.domain.repository.ProductRepository;
import com.erp.main.domain.repository.QuotationRepository;
import com.erp.main.domain.repository.RecivedOrderRepository;

/*
 * 受注のサービス
 * @author nagato
 */

@Service
public class RecivedOrderService {
	/*
	 * 受注レポジトリ
	 */
	@Autowired
	private RecivedOrderRepository recivedOrderReposoitory; 
	
	/**
	 * 見積リポジトリ
	 */
	@Autowired
	private QuotationRepository quotationRepository;

	/**
	 * 商品リポジトリ
	 */
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * 金額コンポーネント
	 */
	@Autowired
	private MoneyComponent moneyComponent;
	
	/**
	 * 取引先マスタのリポジトリ
	 */
	@Autowired
	private ClientsRepository clientsRepository;
	
	/**
	 * 会社マスタのリポジトリ
	 */
	@Autowired
	private CompanyRepository companyRepository;
	
	/**
	 * 部署マスタのリポジトリ
	 */
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	/**
	 * 受注作成処理
	 * @param createRecivedOrderVo
	 */
	@Transactional
	public void  createRecivedOrder(CreateRecivedOrderVo createRecivedOrderVo) {
		
		//取引先の有無の確認		
		Optional<ClientsEntity> clients = this.clientsRepository.findById(createRecivedOrderVo.getClientsSeq());
		if(clients.isEmpty()) {
			throw new AppException(String.format("対象の取引先が取得できません。companySeq: %s",createRecivedOrderVo.getClientsSeq()));
		}
		//会社の有無の確認
		Optional<CompanyEntity> company = this.companyRepository.findById(createRecivedOrderVo.getCompanySeq());
		if(company.isEmpty()) {
			throw new AppException(String.format("対象の会社が取得できません。companySeq: %s",createRecivedOrderVo.getCompanySeq()));
		}
		
		//部署の有無の確認
		Optional<DepartmentEntity> department = this.departmentRepository.findById(createRecivedOrderVo.getDepartmentSeq());
		if(department.isEmpty()) {
			throw new AppException(String.format("対象の部署が取得できません。companySeq: %s",createRecivedOrderVo.getDepartmentSeq()));
		}	
		
		//見積の有無の確認
		Optional<QuotationEntity> quotation = this.quotationRepository.findById(createRecivedOrderVo.getQuotationSeq());
		if(quotation.isEmpty()) {
			throw new AppException(String.format("対象の見積が取得できません。companySeq: %s",createRecivedOrderVo.getQuotationSeq()));
		}	
		
		// 見積詳細の作成
		Set<QuotationDetailEntity> detailEntities = new HashSet<>();
		
		
	}
	

}
