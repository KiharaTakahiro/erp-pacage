package com.erp.main.domain.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.CompanyEntity;
import com.erp.main.domain.objects.entity.DepartmentEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.RecivedOrderDetailEntity;
import com.erp.main.domain.objects.entity.RecivedOrderEntity;
import com.erp.main.domain.objects.model.RecivedOrderModel;
import com.erp.main.domain.objects.valueobjects.CreateRecivedOrderVo;
import com.erp.main.domain.objects.valueobjects.CreateRecivedOrderVo.CreateRecivedOrderDetailVo;
import com.erp.main.domain.objects.valueobjects.GetRecivedOrderConditionsVo;
import com.erp.main.domain.objects.valueobjects.GetRecivedOrderVo;
import com.erp.main.domain.objects.valueobjects.GetRecivedOrdersVo;
import com.erp.main.domain.repository.ClientsRepository;
import com.erp.main.domain.repository.CompanyRepository;
import com.erp.main.domain.repository.DepartmentRepository;
import com.erp.main.domain.repository.ProductRepository;
import com.erp.main.domain.repository.RecivedOrderRepository;
import com.erp.main.domain.specification.RecivedOederSpec;

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
	// TODO: 使われないため一時的にコメントアウトする
	//	@Autowired
	//	private QuotationRepository quotationRepository;

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
		// TODO: 現時点では受注から作るため見積の登録は必須ではないいづれ必須とするかを見直し、必要であればコメントアウトを外す
		//		Optional<QuotationEntity> quotation = this.quotationRepository.findById(createRecivedOrderVo.getQuotationSeq());
		//		if(quotation.isEmpty()) {
		//			throw new AppException(String.format("対象の見積が取得できません。companySeq: %s",createRecivedOrderVo.getQuotationSeq()));
		//		}
		
		// 受注詳細の作成
		Set<RecivedOrderDetailEntity> detailEntities = new HashSet<>();
		// 合計金額
		var totalPrice = 0L;
		// 値引合計
		var discountTotal = 0L;
		
		// 消費税合計
		var taxTotal = 0L;
		
		// 詳細の入力確認
		if(createRecivedOrderVo.getDetails().isEmpty()) {
			throw new AppException("受注詳細が入力されていません");
		}
		
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
			var taxTaype = product.get().getTaxType();
			
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
		
		
		// 合計金額
		recivedOrder.setTotal(totalPrice + taxTotal);
		
		// 受注詳細をセット
		recivedOrder.setRecivedOrderDetailEntity(detailEntities);
		// 受注の保存
		this.recivedOrderRepository.save(recivedOrder);			
	}
	
	/*
	 * 受注一覧取得
	 */
	@Transactional
	public GetRecivedOrdersVo getRecivedOrderVo(GetRecivedOrderConditionsVo condition) {
		
		// nullの場合は1ページ目として取得する
		if(condition.getPageNo() == null) {
			condition.setPageNo(0);
		}
		
		//検索条件
		Specification<RecivedOrderEntity> spec = Specification.where(
			RecivedOederSpec.recivedOrderSeqEquals(condition.getRecivedOrderSeq()))
				.and(RecivedOederSpec.clientsSeqEquals(condition.getClientsSeq()))
				.and(RecivedOederSpec.companySeqEquals(condition.getCompanySeq()))
				.and(RecivedOederSpec.departmentSeqEquals(condition.getDepartmentSeq()))
				.and(RecivedOederSpec.fromDate(condition.getFromDate()))
				.and(RecivedOederSpec.toDate(condition.getToDate()))
				.and(RecivedOederSpec.fromTax(condition.getFromTax()))
				.and(RecivedOederSpec.toTax(condition.getToTax()))
				.and(RecivedOederSpec.fromTotal(condition.getFromTotal()))
				.and(RecivedOederSpec.toTotal(condition.getToTotal()));
				

		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "recivedOrderSeq");
		
		Page<RecivedOrderEntity> pages = this.recivedOrderRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 15, sort));
		
		List<RecivedOrderModel> orders = pages.get().map(e -> {
			var recivedOrder = new RecivedOrderModel();
			//受注Seq
			recivedOrder.setRecivedOrderSeq(e.getRecivedOrderSeq());
			//取引先Seq
			recivedOrder.setClientsSeq(e.getClientsSeq());
			//取引先のエンティティ生成
			Optional<ClientsEntity> client = this.clientsRepository.findById(e.getClientsSeq());
			if(client.isEmpty()) {
				throw new AppException(String.format("対象の取引先が取得できません。clientsSeq: %s",e.getClientsSeq()));
			}
			//取引先名
			recivedOrder.setClientsName(client.get().getName());
			//会社Seq
			recivedOrder.setCompanySeq(e.getCompanySeq());
			//会社のエンティティ生成
			Optional<CompanyEntity> company = this.companyRepository.findById(e.getCompanySeq());
			if(client.isEmpty()) {
				throw new AppException(String.format("対象の会社が取得できません。companySeq: %s",e.getCompanySeq()));
			}
			//会社名
			recivedOrder.setCompanyName(company.get().getName());
			//部署Seq
			recivedOrder.setDepartmentSeq(e.getDepartmentSeq());
			//部署のエンティティ生成
			Optional<DepartmentEntity> department = this.departmentRepository.findById(e.getDepartmentSeq());
			if(department.isEmpty()) {
				throw new AppException(String.format("対象の取引先が取得できません。departmentSeq: %s",e.getDepartmentSeq()));
			}
			//部署名
			recivedOrder.setDepartmentName(department.get().getName());
			//見積Seq
			recivedOrder.setQuotationSeq(e.getQuotationSeq());
			//受注日
			recivedOrder.setRecivedOrderDate(e.getRecivedOrderDate());
			//税金計
			recivedOrder.setTax(e.getTax());
			//合計金額
			recivedOrder.setTotal(e.getTotal());
			
			return recivedOrder;
		}).collect(Collectors.toList());
		
		//詰めるVo生成
		var vo = new GetRecivedOrdersVo();
		//総アイテム数
		vo.setTotalItemsNum(pages.getTotalElements());
		//受注一覧
		vo.setRecivedOder(orders);
		
		return vo;
		
		
		
				
				}
	@Transactional
	public GetRecivedOrderVo getRecivedOrderVo(Long recivedOrderSeq) {
		//該当受注を検索
		Optional<RecivedOrderEntity> order = recivedOrderRepository.findById(recivedOrderSeq);
		if(order.isEmpty()) {
			throw new AppException(String.format("該当の受注を取得できませんでした。 recivedOrderSeq: %s", recivedOrderSeq));
		}
		//マッピング
		var vo = GetRecivedOrderVo.mapTo(order.get());
		return vo;
	}

}
