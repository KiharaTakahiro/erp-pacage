package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.RecivedOrderEntity;

/*
 * 受注用のSpecification
 * @author ngt
 */
public class RecivedOederSpec {
	
	private RecivedOederSpec() {}
	
	
	// 受注Seqによる検索
	public static Specification<RecivedOrderEntity> recivedOrderSeqEquals(Long recivedOrderSeq){
		return recivedOrderSeq == null ? null : (root, query, builder) -> builder.equal(root.get("recivedOrderSeq"), recivedOrderSeq);
	}
	
	//取引先Seqによる検索
	public static Specification<RecivedOrderEntity> clientsSeqEquals(Long clientsSeq){
		return clientsSeq == null ? null : (root, query, builder) -> builder.equal(root.get("clientsSeq"), clientsSeq);
	}
	
	//会社Seqによる検索
	public static Specification<RecivedOrderEntity> companySeqEquals(Long companySeq){
		return companySeq == null ? null : (root, query, builder) -> builder.equal(root.get("companySeq"), companySeq);
	}
	
	//部署Seqによる検索
	public static Specification<RecivedOrderEntity> departmentSeqEquals(Long departmentSeq){
		return departmentSeq == null ? null : (root, query, builder) -> builder.equal(root.get("departmentSeq"), departmentSeq);
	}
	
	//受注日From検索
	public static Specification<RecivedOrderEntity> fromDate(String fromDate){
		return fromDate == null ? null : (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("recivedOrderDate"), fromDate);
	}
	
	//受注日Toで検索
	public static Specification<RecivedOrderEntity> toDate(String fromDate){
		return fromDate == null ? null : (root, query, builder) -> builder.lessThanOrEqualTo(root.get("recivedOrderDate"), fromDate);
	}
	
	//税金Fromで検索
	public static Specification<RecivedOrderEntity> fromTax(Integer fromTax){
		return fromTax == null ? null : (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("tax"), fromTax);
	}
	
	//税金Toで検索
	public static Specification<RecivedOrderEntity> toTax(Integer toTax){
		return toTax == null ? null : (root, query, builder) -> builder.lessThanOrEqualTo(root.get("tax"), toTax);
	}
	
	//合計金額Fromで検索
	public static Specification<RecivedOrderEntity> fromTotal(Integer fromTotal){
		return fromTotal == null ? null : (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("total"), fromTotal);
	}
	
	//合計金額TOで検索
	public static Specification<RecivedOrderEntity> toTotal(Integer toTotal){
		return toTotal == null ? null : (root, query, builder) -> builder.lessThanOrEqualTo(root.get("total"), toTotal);
	}
}
