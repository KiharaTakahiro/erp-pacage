package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.QuotationEntity;

/**
 * 見積用のSpecification
 * @author takah
 *
 */
public class QuotationSpec {
	// 参考の通りだがおそらくstaticファクトリ以外での生成を禁止する目的と思われる
	private QuotationSpec() {}
	

	/**
	 * 見積SEQでの検索
	 * @param clientsSeq
	 * @return
	 */
	public static Specification<QuotationEntity> quotationSeqEquals(Long quotationSeq){
		return quotationSeq == null ? null : (root, query, builder) -> builder.equal(root.get("quotationSeq"), quotationSeq);
	}
	
	/**
	 * 取引先SEQでの検索
	 * @param clientsSeq
	 * @return
	 */
	public static Specification<QuotationEntity> clientsSeqEquals(Long clientsSeq){
		return clientsSeq == null ? null : (root, query, builder) -> builder.equal(root.get("clientsSeq"), clientsSeq);
	}
	
	/**
	 * 部門SEQでの検索
	 * @param clientsSeq
	 * @return
	 */
	public static Specification<QuotationEntity> departmentSeqEquals(Long departmentSeq){
		return departmentSeq == null ? null : (root, query, builder) -> builder.equal(root.get("departmentSeq"), departmentSeq);
	}
	
	/**
	 * 見積番号で検索
	 * @param clientsSeq
	 * @return
	 */
	public static Specification<QuotationEntity> quotationNoLike(String quotationNo){
		return quotationNo == null ? null : (root, query, builder) -> builder.like(root.get("quotationNo"), String.join("%", quotationNo, "%"));
	}
	
	/**
	 * 作成日Fromで検索
	 * @param clientsSeq
	 * @return
	 */
	public static Specification<QuotationEntity> createDateFrom(String createDateFrom){
		return createDateFrom == null ? null : (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("createDate"), createDateFrom);
	}
	
	/**
	 * 作成日Toで検索
	 * @param clientsSeq
	 * @return
	 */
	public static Specification<QuotationEntity> createDateTo(String createDateTo){
		return createDateTo == null ? null : (root, query, builder) -> builder.lessThanOrEqualTo(root.get("createDate"), createDateTo);
	}

}
