package com.erp.main.domain.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.enums.TaxType;
import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.CompanyEntity;
import com.erp.main.domain.objects.entity.DepartmentEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.QuotationEntity;
import com.erp.main.domain.objects.entity.RecivedOrderDetailEntity;
import com.erp.main.domain.objects.entity.RecivedOrderEntity;
import com.erp.main.domain.objects.valueObjects.CreateRecivedOrderVo;
import com.erp.main.domain.objects.valueObjects.CreateRecivedOrderVo.CreateRecivedOrderDetailVo;
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
	private RecivedOrderRepository recivedOrderRepository; 
	
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
		
		// 受注詳細の作成
		Set<RecivedOrderDetailEntity> detailEntities = new HashSet<>();
		// 合計金額
		long totalPrice = 0L;
		// 値引合計
		long discountTotal = 0L;
		
		// 消費税合計
		long taxTotal = 0L;
		
		for(CreateRecivedOrderDetailVo detailVo: createRecivedOrderVo.getDetails()) {
			// 商品の取得
			Optional<ProductEntity> product = this.productRepository.findById(detailVo.getProductSeq());
			if(product.isEmpty()) {
				throw new AppException(String.format("対象の商品が取得できません。productSeq: %s",detailVo.getProductSeq()));
			}
			
			// 値引がマイナスの場合はエラー
			if(detailVo.getDiscount() < 0) {
				throw new AppException(String.format("値引額は正の整数で入力してください。discount: %s",detailVo.getDiscount()));
			}
			
			// 数量がマイナスの場合はエラー
			if(detailVo.getQuantity() < 0) {
				throw new AppException(String.format("数量は正の整数で入力してください。quantity: %s",detailVo.getQuantity()));
			}
			
			
			// 合計金額を加算する
			totalPrice += product.get().getUnitPrice() * detailVo.getQuantity();	
			// 値引を加算する
			discountTotal += detailVo.getDiscount();
			
			// 金額 (単金 × 数量 - 値引)
			long price = product.get().getUnitPrice() * detailVo.getQuantity() - detailVo.getDiscount();
			
			//商品ごとの税金タイプ
			TaxType taxTaype = product.get().getTaxType();
			
			//税金の合計を加算
			taxTotal += this.moneyComponent.computeTax(price, taxTaype);
			
			
			// 受注詳細用のエンティティ生成
			RecivedOrderDetailEntity detailEntity = RecivedOrderDetailEntity.create(detailVo);

			// 見積詳細の追加
			detailEntities.add(detailEntity);
			
		}
		
		RecivedOrderEntity recivedOrder = RecivedOrderEntity.create(createRecivedOrderVo);
		
		// 値引適応
		totalPrice -= discountTotal;
		
		// 消費税
//		long tax = this.moneyComponent.computeTax(totalPrice);
		recivedOrder.setTax(taxTotal);
		
		// 合計金額
		recivedOrder.setTotal(totalPrice + taxTotal);
		
		// 受注詳細をセット
		recivedOrder.setRecivedOrderDetailEntity(detailEntities);
		
		// 受注の保存
		recivedOrder = this.recivedOrderRepository.save(recivedOrder);
	}
	

}
