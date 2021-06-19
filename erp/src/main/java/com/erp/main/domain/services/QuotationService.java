package com.erp.main.domain.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.CompanyEntity;
import com.erp.main.domain.objects.entity.DepartmentEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.QuotationDetailEntity;
import com.erp.main.domain.objects.entity.QuotationEntity;
import com.erp.main.domain.objects.model.QuotationModel;
import com.erp.main.domain.objects.valueobjects.CreateQuotationVo;
import com.erp.main.domain.objects.valueobjects.CreateQuotationVo.CreateQuotationDetailVo;
import com.erp.main.domain.objects.valueobjects.GetQuotationConditionsVo;
import com.erp.main.domain.objects.valueobjects.GetQuotationVo;
import com.erp.main.domain.repository.ClientsRepository;
import com.erp.main.domain.repository.CompanyRepository;
import com.erp.main.domain.repository.DepartmentRepository;
import com.erp.main.domain.repository.ProductRepository;
import com.erp.main.domain.repository.QuotationRepository;
import com.erp.main.domain.specification.QuotationSpec;

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
	 * 見積作成処理
	 * @param createQuotationVo
	 */
	@Transactional
	public void createQuotation(CreateQuotationVo createQuotationVo) {
		
		//取引先の有無の確認		
		Optional<ClientsEntity> clients = this.clientsRepository.findById(createQuotationVo.getClientsSeq());
		if(clients.isEmpty()) {
			throw new AppException(String.format("対象の取引先が取得できません。companySeq: %s",createQuotationVo.getClientsSeq()));
		}
		//会社の有無の確認
		Optional<CompanyEntity> company = this.companyRepository.findById(createQuotationVo.getCompanySeq());
		if(company.isEmpty()) {
			throw new AppException(String.format("対象の会社が取得できません。companySeq: %s",createQuotationVo.getCompanySeq()));
		}
		
		//部署の有無の確認
		Optional<DepartmentEntity> department = this.departmentRepository.findById(createQuotationVo.getDepartmentSeq());
		if(department.isEmpty()) {
			throw new AppException(String.format("対象の部署が取得できません。companySeq: %s",createQuotationVo.getDepartmentSeq()));
		}
		
		
		// 見積詳細の作成
		Set<QuotationDetailEntity> detailEntities = new HashSet<>();
		// 小計
		var subtotal = 0L;
		// 値引合計
		var discountTotal = 0L;
		// 消費税合計
		var taxTotal = 0L;
		
		// 詳細の入力確認
		if(createQuotationVo.getDetails().isEmpty()) {
			throw new AppException("見積詳細が入力されていません");
		}
			

		// 見積詳細作成処理
		for(CreateQuotationDetailVo detailVo: createQuotationVo.getDetails()) {
			
			// 商品の取得
			Optional<ProductEntity> product = this.productRepository.findById(detailVo.getProductSeq());
			if(product.isEmpty()) {
				throw new AppException(String.format("対象の商品が取得できません。productSeq: %s",detailVo.getProductSeq()));
			}
			
			// 見積詳細用のエンティティ生成
			QuotationDetailEntity detailEntity = QuotationDetailEntity.create(detailVo);
			
			// 値引がマイナスの場合はエラー
			if(detailVo.getDiscount() < 0) {
				throw new AppException(String.format("値引額は正の整数で入力してください。discount: %s",detailVo.getDiscount()));
			}
			
			// 数量がマイナスの場合はエラー
			if(detailVo.getQuantity() < 0) {
				throw new AppException(String.format("数量は正の整数で入力してください。quantity: %s",detailVo.getQuantity()));
			}

			
			// 金額 (単金 × 数量 - 値引)
			long price = product.get().getUnitPrice() * detailVo.getQuantity() - detailVo.getDiscount();
			detailEntity.setPrice(price);
			
			//商品ごとの税金タイプ
			var taxTaype = product.get().getTaxType();
			
			//税金の合計を加算
			taxTotal += this.moneyComponent.computeTax(price, taxTaype);
					
			// 小計を加算する
			subtotal += price;
			
			// 値引を加算する
			discountTotal += detailVo.getDiscount();
			
			// 見積詳細の追加
			detailEntities.add(detailEntity);
			
		}
		
		// 値引が設定されていない場合は詳細内の値引の合計額を設定する
		if(createQuotationVo.getDiscountTotal() == null) {
			createQuotationVo.setDiscountTotal(discountTotal);
		}
		
		// 詳細内の値引合計額が合計の値引額を下回っている場合はエラー
		if(discountTotal > createQuotationVo.getDiscountTotal()) {
			String message = String.format("値引合計額が、個別の商品の値引の合計額より少ないです。値引合計額: %s 個別商品の値引の合計: %s", createQuotationVo.getDiscountTotal(), discountTotal);
			throw new AppException(message);
		}
		
		// VOからマッピング可能な部分をマッピング
		QuotationEntity quotation = QuotationEntity.create(createQuotationVo);
		
		// 小計
		quotation.setSubTotal(subtotal);
		
		quotation.setTax(taxTotal);
		// 合計(小計 + 消費税)
		quotation.setTotal(subtotal + taxTotal);
		
		// 見積詳細をセットする
		quotation.setQuotationDetailEntity(detailEntities);
		
		// 見積の保存
		quotation = this.quotationRepository.save(quotation);
	}
	
	/**
	 * 見積取得処理
	 * @param condition
	 * @return
	 */
	public GetQuotationVo getQuotationVo(GetQuotationConditionsVo condition) {		
		// nullの場合は1ページ目として取得する
		if(condition.getPageNo() == null) {
			condition.setPageNo(0);
		}
		
		// 検索条件の設定
		Specification<QuotationEntity> spec = Specification.where(
				QuotationSpec.quotationSeqEquals(condition.getQuotationSeq()))
				.and(QuotationSpec.clientsSeqEquals(condition.getClientsSeq()))
				.and(QuotationSpec.departmentSeqEquals(condition.getDepartmentSeq()))
				.and(QuotationSpec.quotationNoLike(condition.getQuotationNo()))
				.and(QuotationSpec.createDateFrom(condition.getCreateDateFrom()))
				.and(QuotationSpec.createDateTo(condition.getCreateDateTo()));

		
		// FIXME: サイズはymlで設定できるようにする
		Page<QuotationEntity> pages = this.quotationRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 30));
		
		// HACK: 下記の書き方は可読性を落とすと思うため辞めたいが、速度を考えるとこちらの方が早くなる
		// ような気がするため一度実装して見て著しい処理速度の差がない場合は通常の拡張Forで書く予定
		List<QuotationModel> quotations = pages.get().map(e -> {
			var quotation = new QuotationModel();
			// 取引先SEQ
			quotation.setClientsSeq(e.getClientsSeq());
			// 取引先名
			quotation.setClientsName(e.getClientsEntity().getName());				
			// 会社SEQ
			quotation.setCompanySeq(e.getCompanySeq());
			// 会社名
			quotation.setCompanyName(e.getCompanyEntity().getName());
			// 作成日
			quotation.setCreateDate(e.getCreateDate());
			// 部署SEQ
			quotation.setDepartmentSeq(e.getDepartmentSeq());
			// 部署名
			if(e.getDepartmentEntity() != null) {
				quotation.setDepartmentName(e.getDepartmentEntity().getName());				
			}
			// 見積番号
			quotation.setQuotationNo(e.getQuotationNo());
			// 件名
			quotation.setSubject(e.getSubject());			
			return quotation;
		}).collect(Collectors.toList());

		var vo = new GetQuotationVo();
		// トータルページ数の設定
		vo.setMaxpage(pages.getTotalPages());
		// 見積リストの設定
		vo.setQuotations(quotations);
		
		return vo;
	}
	
}
